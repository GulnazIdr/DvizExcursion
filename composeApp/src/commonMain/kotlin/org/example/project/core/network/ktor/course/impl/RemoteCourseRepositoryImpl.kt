package org.example.project.core.network.ktor.course.impl

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.project.core.database.source.LocalCourseRepository
import org.example.project.core.model.Course
import org.example.project.core.model.CourseDetail
import org.example.project.core.model.StepikCourse
import org.example.project.core.model.StepikCourseDetailed
import org.example.project.core.network.NothingFoundException
import org.example.project.core.network.executeApiRequest
import org.example.project.core.network.ktor.course.model.CourseDetailDto
import org.example.project.core.network.ktor.course.model.CourseDto
import org.example.project.core.network.ktor.course.model.StepikCourseDetailedDto
import org.example.project.core.network.ktor.course.model.StepikCourseDto
import org.example.project.core.network.ktor.course.source.RemoteCourseRepository
import org.example.project.core.network.ktor.models.KtorDataWrapping
import org.example.project.core.network.mapper.MetaToPageInfoMapper
import org.example.project.feature.auth.domain.token.TokenRepository

class RemoteCourseRepositoryImpl(
    private val client: HttpClient,
    private val tokenRepository: TokenRepository,
    private val localCourseRepository: LocalCourseRepository,
    private val metaToPageInfoMapper: MetaToPageInfoMapper
) : RemoteCourseRepository {
    override suspend fun getCourses(page: Int): Result<KtorDataWrapping<StepikCourse>> {
        return executeApiRequest<StepikCourse, StepikCourseDto>(
            apiCall = { client.get(urlString = "courses") { parameter("page", page) } },
            refreshToken = { tokenRepository.refreshToken() },
            parseResponse = { it.toStepik(metaToPageInfoMapper) },
            onSuccessAction = { stepikCourse ->
                localCourseRepository.saveCourses(stepikCourse.courses.map {
                    it.toCourseDetailed()
                })
                Result.success(stepikCourse)
            },
            getFromCache = { localCourseRepository.getCourses() }
        )
    }

    override suspend fun getCourseById(id: Int): Result<KtorDataWrapping<StepikCourseDetailed>> {

        return executeApiRequest<StepikCourseDetailed, StepikCourseDetailedDto>(
            apiCall = { client.get(urlString = "courses/$id")  },
            refreshToken = { tokenRepository.refreshToken() },
            parseResponse = { it.toStepikDetailed(metaToPageInfoMapper) },
            onSuccessAction = { stepikDetailedCourse ->
                localCourseRepository.updateCourseDetailed(
                    stepikDetailedCourse.courses.first()
                )
                Result.success(stepikDetailedCourse)
            },
            validateResult = { it.courses.isNotEmpty() },
            validationError = { throw NothingFoundException("course with id $id doesnt exist") },
            getFromCache = { localCourseRepository.getCourseById(id) }
        )
    }

    override suspend fun getCoursesByIds(idList: List<Int>)
            : Result<KtorDataWrapping<StepikCourseDetailed>> {

        return executeApiRequest<StepikCourseDetailed, StepikCourseDetailedDto>(
            apiCall = {
                client.get(urlString = "courses") {
                    idList.forEach { id ->
                        parameter("ids[]", id)
                    }
                }
            },
            refreshToken = { tokenRepository.refreshToken() },
            parseResponse = { it.toStepikDetailed(metaToPageInfoMapper) },
            onSuccessAction = { stepikDetailedCourse ->
                localCourseRepository.updateCourseDetailed(
                    stepikDetailedCourse.courses.first()
                )
                Result.success(stepikDetailedCourse)
            },
            validateResult = { it.courses.isNotEmpty() },
            validationError = {
                throw NothingFoundException(
                    "course with id ${
                    idList.forEach {
                        it
                    }
                } doesnt exist")
            },
            getFromCache = { localCourseRepository.getCoursesByIds(idList) }
        )
    }
}

private fun StepikCourseDto.toStepik(
    metaToPageInfoMapper: MetaToPageInfoMapper
): StepikCourse{
    return StepikCourse(
        pageInfo = metaToPageInfoMapper.map( meta ),
        courses = courses.map { it.toCourse() }
    )
}

private fun StepikCourseDetailedDto.toStepikDetailed(
    metaToPageInfoMapper: MetaToPageInfoMapper
): StepikCourseDetailed{
    return StepikCourseDetailed(
        pageInfo = metaToPageInfoMapper.map( meta ),
        courses = courses.map { it.toCourseDetail() }
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