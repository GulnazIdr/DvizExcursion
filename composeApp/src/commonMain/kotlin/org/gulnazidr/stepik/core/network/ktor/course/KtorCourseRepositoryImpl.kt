package org.gulnazidr.stepik.core.network.ktor.course

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.gulnazidr.stepik.core.domain.cancellationRunCatching
import org.gulnazidr.stepik.core.model.Course
import org.gulnazidr.stepik.core.model.CourseDetail
import org.gulnazidr.stepik.core.model.StepikCourse
import org.gulnazidr.stepik.core.model.StepikCourseDetailed
import org.gulnazidr.stepik.core.network.CustomServerException
import org.gulnazidr.stepik.core.network.RequestTimeOutException
import org.gulnazidr.stepik.core.network.TokenRefreshException
import org.gulnazidr.stepik.core.network.ktor.course.model.CourseDetailDto
import org.gulnazidr.stepik.core.network.ktor.course.model.CourseDto
import org.gulnazidr.stepik.core.network.ktor.course.model.StepikCourseDetailedDto
import org.gulnazidr.stepik.core.network.ktor.course.model.StepikCourseDto
import org.gulnazidr.stepik.core.network.mapper.MetaToPageInfoMapper
import org.gulnazidr.stepik.core.domain.auth.TokenRepository

class KtorCourseRepositoryImpl(
    private val client: HttpClient,
    private val tokenRepository: TokenRepository,
    private val metaToPageInfoMapper: MetaToPageInfoMapper
): KtorCourseRepository {
    override suspend fun getCourses(page: Int): Result<StepikCourse> {
        return cancellationRunCatching {
            client.get(urlString = "courses") { parameter("page", page) }
        }.map { response ->
            when (response.status.value) {
                401 -> {
                    val isRefreshed = tokenRepository.refreshToken()
                    if (!isRefreshed) {
                        throw TokenRefreshException("failed to refresh token")
                    } else {
                        getCourses(page)
                    }
                }

                408 -> throw RequestTimeOutException("waiting time exceeded")

                in 500..511 -> throw CustomServerException("server error $response")
            }

            response.body<StepikCourseDto>().toStepik(metaToPageInfoMapper)
        }
    }

    override suspend fun getCourseById(id: Int): Result<StepikCourseDetailed> {

        return cancellationRunCatching {
            client.get(urlString = "courses/$id")
        }.map { response ->
            when (response.status.value) {
                401 -> {
                    val isRefreshed = tokenRepository.refreshToken()
                    if (!isRefreshed) {
                        throw TokenRefreshException("failed to refresh token")
                    } else {
                        getCourseById(id)
                    }
                }

                408 -> throw RequestTimeOutException("waiting time exceeded")

                in 500..511 -> throw CustomServerException("server error $response")
            }
            response.body<StepikCourseDetailedDto>().toStepikDetailed(metaToPageInfoMapper)
        }
    }

    override suspend fun getCoursesByIds(idList: List<Int>): Result<StepikCourseDetailed> {
        return cancellationRunCatching {
            client.get(urlString = "courses") {
                idList.forEach { id ->
                    parameter("ids[]", id)
                }
            }
        }.map { response ->
            when (response.status.value) {
                401 -> {
                    val isRefreshed = tokenRepository.refreshToken()
                    if (!isRefreshed) {
                        throw TokenRefreshException("failed to refresh token")
                    } else {
                        getCoursesByIds(idList)
                    }
                }

                408 -> throw RequestTimeOutException("waiting time exceeded")

                in 500..511 -> throw CustomServerException("server error $response")
            }
            response.body<StepikCourseDetailedDto>().toStepikDetailed(metaToPageInfoMapper)
        }
    }


    private fun StepikCourseDto.toStepik(
        metaToPageInfoMapper: MetaToPageInfoMapper
    ): StepikCourse {
        return StepikCourse(
            pageInfo = metaToPageInfoMapper.map(meta),
            courses = courses.map { it.toCourse() }
        )
    }

    private fun StepikCourseDetailedDto.toStepikDetailed(
        metaToPageInfoMapper: MetaToPageInfoMapper
    ): StepikCourseDetailed {
        return StepikCourseDetailed(
            pageInfo = metaToPageInfoMapper.map(meta),
            courses = courses.map { it.toCourseDetail() }
        )
    }

    private fun CourseDto.toCourse(): Course {
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

    private fun CourseDetailDto.toCourseDetail(): CourseDetail {
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
}