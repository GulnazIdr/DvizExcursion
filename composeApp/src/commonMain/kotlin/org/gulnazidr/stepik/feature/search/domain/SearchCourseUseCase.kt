package org.gulnazidr.stepik.feature.search.domain

import org.gulnazidr.stepik.core.common.result.FetchDataResult
import org.gulnazidr.stepik.core.common.result.FetchError
import org.gulnazidr.stepik.core.common.result.parseExceptionToNetworkError
import org.gulnazidr.stepik.core.domain.courses.result.CourseSuccessResult
import org.gulnazidr.stepik.core.domain.courses.source.CourseRepository
import org.gulnazidr.stepik.core.model.Course

class SearchCourseUseCase(
    private val courseRepository: CourseRepository
) {
    private var currentPage = 1

    private val _courseList: MutableList<Course> = mutableListOf()
    private var _courseFetchResult: FetchDataResult<CourseSuccessResult, FetchError?>? = null

    suspend operator fun invoke(keyValue: String)
            : FetchDataResult<CourseSuccessResult, FetchError?> {
        val result = courseRepository.getCourses(currentPage)

        result.onSuccess { stepik ->
            if (stepik.data.pageInfo.hasNext) {
                currentPage++
            }

            _courseList += stepik.data.courses

            if (_courseList.isEmpty()) {
                invoke(keyValue)
            }

            if (_courseList.size < 20) {
                invoke(keyValue)
            }
            val courseSuccessData = CourseSuccessResult(
                _courseList.filter { course ->
                    course.title.lowercase().contains(keyValue) ||
                            course.description.lowercase().contains(keyValue)
                },
                stepik.data.pageInfo.hasNext
            )

            _courseFetchResult = if (stepik.isFromCache) {
                FetchDataResult.Cache(
                    cacheData = courseSuccessData,
                    error = parseExceptionToNetworkError(stepik.error)
                )
            } else {
                FetchDataResult.Success(
                    CourseSuccessResult(
                        _courseList.filter { course ->
                            course.title.lowercase().contains(keyValue) ||
                                    course.description.lowercase().contains(keyValue)
                        },
                        stepik.data.pageInfo.hasNext
                    )
                )
            }


        }.onFailure { exception ->
            _courseFetchResult = FetchDataResult.Error(
                error = parseExceptionToNetworkError(exception)!!
            )
        }

        return _courseFetchResult!!
    }
}
