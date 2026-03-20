package org.example.project.feature.course_detail.domain

import kotlinx.coroutines.flow.update
import org.example.project.core.common.result.FetchResult
import org.example.project.core.common.result.NetworkError
import org.example.project.core.database.source.LocalCourseRepository
import org.example.project.core.designsystem.ui_logic.mapper.asUiText
import org.example.project.core.designsystem.ui_logic.result.FetchResultUi
import org.example.project.core.designsystem.ui_logic.result.FetchResultUi.Success
import org.example.project.core.model.CourseDetail
import org.example.project.core.model.StepikDetailed
import org.example.project.core.network.ktor.source.RemoteCourseRepository

class GetCourseById(
    private val remoteCourseRepository: RemoteCourseRepository,
    private val localCourseRepository: LocalCourseRepository
) {
    suspend operator fun invoke(id: Int){
        when (val res = remoteCourseRepository.getCourseById(id)) {
//            is FetchResult.Success<StepikDetailed> -> {
//                val courses = res.successData.courses
//                val data = if (courses.isNotEmpty()) courses.first() else null
//
//                _currentCourseState.update { state ->
//                    state.copy(
//                        courseState =
//                            if (data != null) Success(data.toCourseDetailUi())
//                            else FetchResultUi.Error(NetworkError.UNKNOWN.asUiText()),
//                        isRefreshing = false
//                    )
//                }
//
//                if (data != null) {
//                    localCourseRepository.updateCourseDetailed(data)
//                }
//            }
//
//            is FetchResult.Cache<CourseDetail?, NetworkError> -> {
//                _currentCourseState.update { state ->
//                    state.copy(
//                        courseState = FetchResultUi.Cached(
//                            cacheData = res.cacheData?.toCourseDetailUi(),
//                            reason = res.cacheError.asUiText()
//                        ),
//                        isRefreshing = false
//                    )
//                }
//            }
        }
    }
}