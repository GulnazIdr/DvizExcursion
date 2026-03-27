package org.example.project.feature.course_catalog.domain

import org.example.project.core.common.result.FetchDataResult
import org.example.project.core.common.result.NetworkError
import org.example.project.core.common.result.parseExceptionToNetworkError
import org.example.project.core.domain.courses.source.CourseRepository
import org.example.project.core.domain.courses.result.CourseSuccessResult
import org.example.project.core.model.Course
import org.example.project.core.model.StepikCourse
import org.example.project.core.network.ktor.models.DataWrapping

class FetchCoursesUseCase(
    private val courseRepository: CourseRepository
) {
    private var currentPage = 1

    private val _courseList: MutableList<Course> = mutableListOf()
    private var _courseFetchResult: FetchDataResult<CourseSuccessResult, NetworkError?>? = null

    suspend operator fun invoke(
        isRefreshing: Boolean
    ): FetchDataResult<CourseSuccessResult, NetworkError?> {

        var result: Result<DataWrapping<StepikCourse>>

//        if (isRefreshing){
//            result = courseRepository.refreshCourses(currentPage)
//        }else {
            if (_courseFetchResult != null && !isRefreshing) {
                return _courseFetchResult!!
            }else{
                result = courseRepository.refreshCourses(currentPage)
            }
//        }

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