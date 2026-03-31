package org.gulnazidr.stepik.feature.course_catalog.domain

import org.gulnazidr.stepik.core.domain.courses.source.CourseRepository
import org.gulnazidr.stepik.core.model.Course
import org.gulnazidr.stepik.core.model.CourseDetail
import org.gulnazidr.stepik.core.model.StepikCourse
import org.gulnazidr.stepik.core.model.StepikCourseDetailed
import org.gulnazidr.stepik.core.common.result.executeApiRequest
import org.gulnazidr.stepik.core.network.ktor.models.DataWrapping
import org.gulnazidr.stepik.feature.course_catalog.domain.local.LocalCourseRepository
import org.gulnazidr.stepik.feature.course_catalog.domain.remote.RemoteCourseRepository

class CourseRepositoryImpl(
    private val localCourseRepository: LocalCourseRepository,
    private val remoteCourseRepository: RemoteCourseRepository
): CourseRepository {

    override suspend fun getCourses(page: Int): Result<DataWrapping<StepikCourse>> {
        return remoteCourseRepository.getCourses(page).executeApiRequest(
            onSuccessAction = { stepikCourse ->
                localCourseRepository.saveCourses(stepikCourse.courses.map {
                    it.toCourseDetailed()
                })
            },
            getFromCache = { localCourseRepository.getCourses() }
        )
    }

    override suspend fun getCourseById(id: Int): Result<DataWrapping<StepikCourseDetailed>> {
        return remoteCourseRepository.getCourseById(id).executeApiRequest(
            onSuccessAction = { stepikDetailedCourse ->
                localCourseRepository.updateCourseDetailed(
                    stepikDetailedCourse.courses.first()
                )
            },
            getFromCache = { localCourseRepository.getCourseById(id) }
        )
    }

    override suspend fun getCoursesByIds(idList: List<Int>): Result<DataWrapping<StepikCourseDetailed>> {
        return remoteCourseRepository.getCoursesByIds(idList).executeApiRequest(
            onSuccessAction = { stepikDetailedCourse ->
                localCourseRepository.updateCourseDetailed(
                    stepikDetailedCourse.courses.first()
                )
            },
            getFromCache = { localCourseRepository.getCoursesByIds(idList) }
        )
    }

//    override suspend fun refreshCourses(page: Int): Result<DataWrapping<StepikCourse>> {
//        return remoteCourseRepository.getCourses(page).fold(
//            onSuccess = { stepikCourse ->
//                localCourseRepository.saveCourses(stepikCourse.courses.map {
//                    it.toCourseDetailed()
//                })
//                Result.success(
//                    DataWrapping(
//                        data = stepikCourse,
//                        isFromCache = false
//                    )
//                )
//            },
//            onFailure = { error ->
//                if (error is TokenRefreshException) {
//                    Result.failure<StepikCourse>(error)
//                }
//                localCourseRepository.getCourses().fold(
//                    onSuccess = { res ->
//                        Result.success(
//                            DataWrapping(
//                                data = res,
//                                isFromCache = true,
//                                error = error
//                            )
//                        )
//                    },
//                    onFailure = { throwable ->
//                        Napier.e("fetching course cache error $throwable")
//                        Napier.e("fetching course remote error $error")
//                        Result.failure(error)
//                    }
//                )
//            }
//        )
//    }
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