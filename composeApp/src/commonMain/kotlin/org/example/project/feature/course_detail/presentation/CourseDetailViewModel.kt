package org.example.project.feature.course_detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.common.result.FetchDataResult
import org.example.project.core.common.result.NetworkError
import org.example.project.core.designsystem.ui_logic.UiText
import org.example.project.core.designsystem.ui_logic.mapper.CourseDetailToCourseDetailUiMapper
import org.example.project.core.designsystem.ui_logic.mapper.asUiText
import org.example.project.core.designsystem.ui_logic.result.FetchResultUi
import org.example.project.core.designsystem.ui_logic.result.FetchResultUi.Error
import org.example.project.core.designsystem.ui_logic.result.FetchResultUi.Success
import org.example.project.core.model.CourseDetail
import org.example.project.feature.course_detail.domain.FetchCourseDetailUseCase

class CourseDetailViewModel(
    private val courseId: Int,
    private val fetchCourseDetailUseCase: FetchCourseDetailUseCase,
    private val courseDetailToCourseDetailUiMapper: CourseDetailToCourseDetailUiMapper
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
            when (val res = fetchCourseDetailUseCase(id)) {
                is FetchDataResult.Success<CourseDetail> -> {
                    val data = res.data

                    _currentCourseState.update { state ->
                        state.copy(
                            courseState = Success(courseDetailToCourseDetailUiMapper.map(data)),
                            isRefreshing = false
                        )
                    }
                }

                is FetchDataResult.Cache<CourseDetail, NetworkError?> -> {
                    _currentCourseState.update { state ->
                        state.copy(
                            courseState = Error(
                                cacheData = courseDetailToCourseDetailUiMapper.map(res.cacheData),
                                message = res.error?.asUiText() ?: UiText.DynamicString("")
                            ),
                            isRefreshing = false
                        )
                    }
                }

                is FetchDataResult.Error<NetworkError?> -> {
                    _currentCourseState.update { state ->
                        state.copy(
                            courseState = Error(
                                message = res.error?.asUiText() ?: UiText.DynamicString("")
                            ),
                            isRefreshing = false
                        )
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        fetchSpecificCourseJob?.cancel()
        fetchSpecificCourseJob = null
    }
}