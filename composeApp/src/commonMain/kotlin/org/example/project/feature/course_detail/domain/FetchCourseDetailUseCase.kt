package org.example.project.feature.course_detail.domain

import org.example.project.core.common.result.FetchDataResult
import org.example.project.core.common.result.NetworkError
import org.example.project.core.common.result.parseExceptionToNetworkError
import org.example.project.core.model.CourseDetail
import org.example.project.feature.course_catalog.domain.remote.RemoteCourseRepository

class FetchCourseDetailUseCase(
    private val remoteCourseRepository: RemoteCourseRepository
) {
    private var _courseDetailed: CourseDetail? = null
    private var _courseFetchResult: FetchDataResult<CourseDetail, NetworkError?>? = null

    suspend operator fun invoke(id: Int): FetchDataResult<CourseDetail, NetworkError?> {
        val result = remoteCourseRepository.getCourseById(id)

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