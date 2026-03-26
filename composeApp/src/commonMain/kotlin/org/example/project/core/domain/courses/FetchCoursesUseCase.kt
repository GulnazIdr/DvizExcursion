package org.example.project.core.domain.courses

import org.example.project.core.common.result.FetchDataResult
import org.example.project.core.common.result.NetworkError
import org.example.project.core.common.result.parseExceptionToNetworkError
import org.example.project.core.model.Course
import org.example.project.core.network.ktor.course.source.RemoteCourseRepository

class FetchCoursesUseCase(
    private val remoteCourseRepository: RemoteCourseRepository
) {
    private var currentPage = 1

    private val _courseList: MutableList<Course> = mutableListOf()
    private var _courseFetchResult: FetchDataResult<CourseSuccessResult, NetworkError?>? = null

    fun getCourseFetchResult(): FetchDataResult<CourseSuccessResult, NetworkError?>? = _courseFetchResult

    suspend operator fun invoke(
        isRefreshing: Boolean
    ): FetchDataResult<CourseSuccessResult, NetworkError?> {

        if(_courseFetchResult != null && !isRefreshing){
            return _courseFetchResult!!
        }

        val result = remoteCourseRepository.getCourses(currentPage)

        result.onSuccess { stepik ->
            if (stepik.data.pageInfo.hasNext) {
                currentPage++
            }

            _courseList += stepik.data.courses

            if (_courseList.isEmpty()) {
                invoke(isRefreshing)
            }

            if (_courseList.size < 20) {
                invoke(isRefreshing)
            }
            val courseSuccessData = CourseSuccessResult(
                _courseList,
                stepik.data.pageInfo.hasNext
            )

            _courseFetchResult = if (stepik.isFromCache) {
                //отображение кеша с ошибкой
                FetchDataResult.Cache(
                    cacheData = courseSuccessData,
                    error = parseExceptionToNetworkError(stepik.error)
                )
            } else {
                FetchDataResult.Success(
                    CourseSuccessResult(
                        _courseList,
                        stepik.data.pageInfo.hasNext
                    )
                )
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
data class CourseSuccessResult(
    val successData: List<Course>,
    val hasNext: Boolean
)

