package org.example.project.feature.course_detail.domain

import org.example.project.core.common.result.FetchCourseResult
import org.example.project.core.common.result.NetworkError
import org.example.project.core.common.result.parseExceptionToNetworkError
import org.example.project.core.model.CourseDetail
import org.example.project.core.network.ktor.source.RemoteCourseRepository

class FetchCourseDetailUseCase(
    private val remoteCourseRepository: RemoteCourseRepository
) {
    private var _courseDetailed: CourseDetail? = null
    private var _courseFetchResult: FetchCourseResult<CourseDetail, NetworkError?>? = null

    suspend operator fun invoke(id: Int): FetchCourseResult<CourseDetail, NetworkError?> {
        val result = remoteCourseRepository.getCourseById(id)

        result.onSuccess { stepik ->
            _courseDetailed = stepik.data.courses.first()

            if (stepik.isFromCache){
                _courseFetchResult = FetchCourseResult.Cache(
                    _courseDetailed!!,
                    parseExceptionToNetworkError(stepik.error)
                )
            }else {
                _courseFetchResult = FetchCourseResult.Success(_courseDetailed!!)
            }
        }.onFailure { exception ->
            //нет кэша
            _courseFetchResult = FetchCourseResult.Error(
                error = parseExceptionToNetworkError(exception)!!
            )
        }

        return _courseFetchResult!!
    }
}