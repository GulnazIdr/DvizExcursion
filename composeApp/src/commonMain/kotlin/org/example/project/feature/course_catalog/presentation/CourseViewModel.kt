package org.example.project.feature.course_catalog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
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
import org.example.project.core.designsystem.ui_logic.mapper.CourseToCourseDetailUiMapper
import org.example.project.core.designsystem.ui_logic.mapper.asUiText
import org.example.project.core.designsystem.ui_logic.model.CourseUiState
import org.example.project.core.designsystem.ui_logic.result.FetchResultUi
import org.example.project.core.designsystem.ui_logic.result.FetchResultUi.Error
import org.example.project.core.designsystem.ui_logic.result.FetchResultUi.Success
import org.example.project.core.domain.CourseSuccessResult
import org.example.project.core.domain.FetchCoursesUseCase

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class CourseViewModel(
    private val fetchCoursesUseCase: FetchCoursesUseCase,
    private val courseToCourseDetailUiMapper: CourseToCourseDetailUiMapper,
    private val courseDetailUi: CourseDetailToCourseDetailUiMapper
) : ViewModel() {
    private var fetchJob: Job? = null

    private val _courseFetchedState = MutableStateFlow(
        CourseUiState(
            isDataLoading = true,
            isPageEnded = false,
            courseFetchedResult = FetchResultUi.Loading(),
            isRefreshing = false
        )
    )

    val courseFetchedState: StateFlow<CourseUiState> = _courseFetchedState.asStateFlow()

    init {
        fetchCourses()
    }

    fun refresh() {
        _courseFetchedState.update { state ->
            state.copy(
                isRefreshing = true
            )
        }
        if (_courseFetchedState.value.isDataLoading) {
            fetchJob?.cancel()
        }
        fetchCourses(true)
    }

    fun fetchCourses(isRefreshing: Boolean = false) {
        fetchJob = viewModelScope.launch {
            _courseFetchedState.update { state ->
                state.copy(
                    isDataLoading = true
                )
            }
            when (val result = fetchCoursesUseCase(isRefreshing = isRefreshing)) {
                is FetchDataResult.Success<CourseSuccessResult> -> {
                    if (!result.data.hasNext) {
                        _courseFetchedState.update { state ->
                            state.copy(
                                isPageEnded = true,
                                isDataLoading = false,
                                isRefreshing = false
                            )
                        }
                    }

                    _courseFetchedState.update { state ->
                        state.copy(
                            isDataLoading = false,
                            courseFetchedResult = Success(
                                result.data.successData.map(
                                    courseToCourseDetailUiMapper::map
                                )
                            ),
                            isRefreshing = false
                        )
                    }
                }

                is FetchDataResult.Error -> {
                    _courseFetchedState.update { state ->
                        state.copy(
                            courseFetchedResult = Error(
                                message = result.error?.asUiText() ?: NetworkError.UNKNOWN.asUiText()
                            ),
                            isDataLoading = false,
                            isRefreshing = false
                        )
                    }
                }

                is FetchDataResult.Cache -> {
                    _courseFetchedState.update { state ->
                        state.copy(
                            courseFetchedResult = Error(
                                message = result.error?.asUiText() ?: UiText.DynamicString(""),
                                cacheData = courseToCourseDetailUiMapper.map(result.cacheData.successData)
                            ),
                            isDataLoading = false,
                            isRefreshing = false
                        )
                    }
                }
            }

            //UknoknwHostException - connecting in topbar
            //408 Request Timeout: истекло время ожидания - reload icon
            //429 Too Many Requests: слишком много запросов - errordialog
        }
    }

    override fun onCleared() {
        super.onCleared()
        fetchJob?.cancel()
        fetchJob = null
    }
}