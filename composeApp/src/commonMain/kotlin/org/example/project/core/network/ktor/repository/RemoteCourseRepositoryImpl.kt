package org.example.project.core.network.ktor.repository

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.project.core.database.source.LocalCourseRepository

import org.example.project.core.model.Course
import org.example.project.core.model.CourseDetail
import org.example.project.core.model.PageInfo
import org.example.project.core.model.Stepik
import org.example.project.core.model.StepikDetailed
import org.example.project.core.network.ktor.CustomServerException
import org.example.project.core.network.ktor.HttpRoutes
import org.example.project.core.network.ktor.NothingFoundException
import org.example.project.core.network.ktor.model.CourseDetailDto
import org.example.project.core.network.ktor.model.CourseDto
import org.example.project.core.network.ktor.model.KtorDataWrapping
import org.example.project.core.network.ktor.model.MetaDto
import org.example.project.core.network.ktor.model.StepikCourseDetailedDto
import org.example.project.core.network.ktor.model.StepikDto
import org.example.project.core.network.ktor.source.RemoteCourseRepository

class RemoteCourseRepositoryImpl(
    private val client: HttpClient,
    private val localCourseRepository: LocalCourseRepository
) : RemoteCourseRepository {
    override suspend fun getCourses(page: Int): Result<KtorDataWrapping<Stepik>> {

        // TODO: add cancellationexception 
        return runCatching {
            //testing throw UnknownHostException("")
            client.get(urlString = HttpRoutes.COURSES) { parameter("page", page) }
        }.map { response ->
            when (response.status.value) {
                in 500..511 -> throw CustomServerException("server error $response")
            }
            KtorDataWrapping(
                data = response.body<StepikDto>().toStepik(),
                isFromCache = false
            )
        }.fold(
            onSuccess = { ktorWrapping ->
                localCourseRepository.saveCourses(ktorWrapping.data.courses.map {
                    it.toCourseDetailed()
                })
                Result.success(ktorWrapping)
            },
            onFailure = { error ->
                val cacheRes = localCourseRepository.getCourses()
                cacheRes.fold(
                    onSuccess = { stepik ->
                        Result.success(
                            KtorDataWrapping(
                                data = stepik,
                                isFromCache = true,
                                error = error
                            )
                        )
                    },
                    onFailure = { throwable ->
                        Napier.e("fetching course cache error $throwable")
                        Napier.e("fetching course remote error $error")
                        Result.failure(error)
                    }
                )
            }
        )
    }

    override suspend fun getCourseById(id: Int): Result<KtorDataWrapping<StepikDetailed>> {

        return runCatching {
            client.get(urlString = HttpRoutes.COURSE_DETAILS + "$id") { }
        }.map { response ->
            KtorDataWrapping(
                data = response.body<StepikCourseDetailedDto>().toStepikDetailed(),
                isFromCache = false
            )
        }.onSuccess { ktorWrapping ->

            if (ktorWrapping.data.courses.isEmpty()) throw NothingFoundException("course with id $id doesnt exist")

            localCourseRepository.updateCourseDetailed(ktorWrapping.data.courses.first())

            Result.success(ktorWrapping)
        }.onFailure { error ->
            val cacheRes = localCourseRepository.getCourseById(id)
            cacheRes.fold(
                onSuccess = { stepikDetailed ->
                    Result.success(
                        KtorDataWrapping(
                            data = stepikDetailed,
                            isFromCache = true
                        )
                    )
                },
                onFailure = { throwable ->
                    Napier.e("fetching course cache error $throwable")
                    Napier.e("fetching course remote error $error")
                    Result.failure<KtorDataWrapping<StepikDetailed?>>(error)
                }
            )
        }
    }
}

private fun StepikDto.toStepik(): Stepik{
    return Stepik(
        pageInfo = meta.toPageInfo(),
        courses = courses.map { it.toCourse() }
    )
}

private fun StepikCourseDetailedDto.toStepikDetailed(): StepikDetailed{
    return StepikDetailed(
        pageInfo = meta.toPageInfo(),
        courses = courses.map { it.toCourseDetail() }
    )
}

private fun MetaDto.toPageInfo(): PageInfo{
    return PageInfo(
        page = page,
        hasNext = hasNext,
        hasPrevious = hasPrevious
    )
}

private fun CourseDto.toCourse(): Course{
    return Course(
        id = id,
        title = title,
        description = summary,
        image = cover ?: "",
        commentAmount = 0,
        favoriteAmount = 0,
        price = price?.toDoubleOrNull() ?: 0.0,
        learnersCount = learners_count
    )
}

private fun CourseDetailDto.toCourseDetail(): CourseDetail{
    return CourseDetail(
        courseBaseInfo = Course(
            id = id,
            title = title,
            description = summary,
            image = cover ?: "",
            commentAmount = 0,
            favoriteAmount = 0,
            price = price?.toDoubleOrNull() ?: 0.0,
            learnersCount = learnersCount
        ),
        workloadTime = workload,
        targetAudience = targetAudience,
        requirements = requirements,
        difficultyLevel = difficulty.orEmpty(),
        acquiredSkills = acquiredSkills,
        acquiredAssets = acquiredAssets,
        learningFormat = learningFormat,
        lessonsCount = lessonsCount
    )
}

private fun Course.toCourseDetailed(): CourseDetail{
    return CourseDetail(
        courseBaseInfo = this,
        workloadTime = "",
        targetAudience = "",
        requirements = "",
        difficultyLevel = "",
        acquiredSkills = listOf(),
        acquiredAssets = listOf(),
        learningFormat = "",
        lessonsCount = 0
    )
}