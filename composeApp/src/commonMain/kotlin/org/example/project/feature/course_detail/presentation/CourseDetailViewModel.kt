package org.example.project.feature.course_detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.common.result.FetchResult
import org.example.project.core.common.result.NetworkError
import org.example.project.core.designsystem.ui_logic.mapper.asUiText
import org.example.project.core.designsystem.ui_logic.result.FetchResultUi
import org.example.project.core.designsystem.ui_logic.result.FetchResultUi.Success
import org.example.project.core.model.CourseDetail
import org.example.project.core.model.StepikDetailed

class CourseDetailViewModel(
    private val courseId: Int,
) : ViewModel() {
    private val _currentCourseState = MutableStateFlow(
        CourseDetailUiState(false, FetchResultUi.Loading())
    )
    val currentCourseState: StateFlow<CourseDetailUiState> = _currentCourseState.asStateFlow()

    init {
        getCourseById(courseId)
    }
    private var fetchSpecificCourseJob: Job? = null

    fun refresh(id: Int) {
        _currentCourseState.update { state ->
            state.copy(
                isRefreshing = true
            )
        }
        getCourseById(id)
    }

    private fun getCourseById(id: Int) {
        fetchSpecificCourseJob?.cancel()

        fetchSpecificCourseJob = viewModelScope.launch {
//            when (val res = remoteCourseRepository.getCourseById(id)) {
//                is FetchResult.Success<StepikDetailed> -> {
//                    val courses = res.successData.courses
//                    val data = if (courses.isNotEmpty()) courses.first() else null
//
//                    _currentCourseState.update { state ->
//                        state.copy(
//                            courseState =
//                                if (data != null) Success(data.toCourseDetailUi())
//                                else FetchResultUi.Error(NetworkError.UNKNOWN.asUiText()),
//                            isRefreshing = false
//                        )
//                    }
//
//                    if (data != null) {
//                        localCourseRepository.updateCourseDetailed(data)
//                    }
//                }
//
//                is FetchResult.Cache<CourseDetail?, NetworkError> -> {
//                    _currentCourseState.update { state ->
//                        state.copy(
//                            courseState = FetchResultUi.Cached(
//                                cacheData = res.cacheData?.toCourseDetailUi(),
//                                reason = res.cacheError.asUiText()
//                            ),
//                            isRefreshing = false
//                        )
//                    }
//                }
//            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        fetchSpecificCourseJob?.cancel()
        fetchSpecificCourseJob = null
    }
}