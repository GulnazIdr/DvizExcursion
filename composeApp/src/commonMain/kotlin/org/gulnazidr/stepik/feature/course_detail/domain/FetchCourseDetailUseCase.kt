package org.gulnazidr.stepik.feature.course_detail.domain

import org.gulnazidr.stepik.core.common.result.FetchDataResult
import org.gulnazidr.stepik.core.common.result.FetchError
import org.gulnazidr.stepik.core.common.result.parseExceptionToNetworkError
import org.gulnazidr.stepik.core.domain.courses.source.CourseRepository
import org.gulnazidr.stepik.core.model.CourseDetail

class FetchCourseDetailUseCase(
    private val courseRepository: CourseRepository
) {
    private var _courseDetailed: CourseDetail? = null
    private var _courseFetchResult: FetchDataResult<CourseDetail, FetchError?>? = null

    suspend operator fun invoke(id: Int): FetchDataResult<CourseDetail, FetchError?> {
        val result = courseRepository.getCourseById(id)

        result.onSuccess { stepik ->
            _courseDetailed = stepik.data.courses.first()

            if (stepik.isFromCache){
                _courseFetchResult = FetchDataResult.Cache(
                    _courseDetailed!!,
                    parseExceptionToNetworkError(stepik.error)
                )
            }else {
                _courseFetchResult = FetchDataResult.Success(_courseDetailed!!)
            }
        }.onFailure { exception ->
            //нет кэша
            _courseFetchResult = FetchDataResult.Error(
                error = parseExceptionToNetworkError(exception)!!
            )
        }

        return _courseFetchResult!!
    }
}