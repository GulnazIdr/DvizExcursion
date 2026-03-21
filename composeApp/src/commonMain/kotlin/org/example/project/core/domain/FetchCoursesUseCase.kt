package org.example.project.core.domain

import io.github.aakira.napier.Napier
import org.example.project.core.common.result.NetworkError
import org.example.project.core.database.CustomRoomException
import org.example.project.core.model.Course
import org.example.project.core.network.ktor.CustomServerException
import org.example.project.core.network.ktor.source.RemoteCourseRepository
import java.net.UnknownHostException

class FetchCoursesUseCase(
    private val remoteCourseRepository: RemoteCourseRepository
) {
    private var currentPage = 1

    private val _courseList: MutableList<Course> = mutableListOf()
    private var _courseFetchResult: FetchCourseResult? = null

    fun getCourseFetchResult(): FetchCourseResult? = _courseFetchResult

    suspend operator fun invoke(): FetchCourseResult {
        val result = remoteCourseRepository.getCourses(currentPage)

        result.onSuccess { stepik ->
            if (stepik.data.pageInfo.hasNext) {
                currentPage++
            }

            _courseList += stepik.data.courses

            if (_courseList.isEmpty()) {
                invoke()
            }

            if (_courseList.size < 20) {
                invoke()
            }
            val courseSuccessData = CourseSuccessResult(
                _courseList,
                stepik.data.pageInfo.hasNext
            )

            if (stepik.isFromCache){
                //отображение кеша с ошибкой
                _courseFetchResult = FetchCourseResult.Cache(
                    cacheData = courseSuccessData,
                    error = parseExceptionToNetworkError(stepik.error)
                )
            }else {
                _courseFetchResult = FetchCourseResult.Success(
                    CourseSuccessResult(
                        _courseList,
                        stepik.data.pageInfo.hasNext
                    )
                )
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

sealed class FetchCourseResult(){
    data class Success(val stepikData: CourseSuccessResult): FetchCourseResult()
    data class Error(val error: NetworkError): FetchCourseResult()
    data class Cache(val cacheData: CourseSuccessResult, val error: NetworkError?): FetchCourseResult()
}

data class CourseSuccessResult(
    val successData: List<Course>,
    val hasNext: Boolean
)

fun parseExceptionToNetworkError(exception: Throwable?): NetworkError?{
    return when (exception) {
        is CustomRoomException -> {
            NetworkError.SERVER_ERROR
        }

        is CustomServerException -> {
            NetworkError.SERVER_ERROR
        }

        is UnknownHostException -> {
            NetworkError.NO_INTERNET
        }

        null ->{
            null
        }

        else -> {
            NetworkError.UNKNOWN
        }
    }
}