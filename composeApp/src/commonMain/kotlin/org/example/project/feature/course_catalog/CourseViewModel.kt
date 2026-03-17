package org.example.project.feature.course_catalog

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
import org.example.project.core.designsystem.ui_logic.FetchCoursesUseCase
import org.example.project.core.designsystem.ui_logic.model.CourseUiState
import org.example.project.feature.course_catalog.presentation.result.FetchResultUi

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class CourseViewModel(
    private val fetchCoursesUseCase: FetchCoursesUseCase
): ViewModel() {
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

    fun refresh(){
        _courseFetchedState.update { state ->
            state.copy(
                isRefreshing = true
            )
        }
        if (_courseFetchedState.value.isDataLoading) {
            fetchJob?.cancel()
        }
        fetchCourses()
    }

    fun fetchCourses() {
        fetchJob = viewModelScope.launch {
            fetchCoursesUseCase(_courseFetchedState)
        }
    }

    override fun onCleared() {
        super.onCleared()
        fetchJob?.cancel()
        fetchJob = null
    }
}