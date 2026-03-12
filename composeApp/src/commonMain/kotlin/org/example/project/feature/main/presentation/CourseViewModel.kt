package org.example.project.feature.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.example.project.core.network.ktor.FetchCoursesUseCase
import org.example.project.feature.main.presentation.models.CourseUiState
import org.example.project.feature.main.presentation.result.FetchResultUi

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class CourseViewModel(
    private val fetchCoursesUseCase: FetchCoursesUseCase
): ViewModel() {
    private var fetchJob: Job? = null

    private val _courseFetchedState = MutableStateFlow(
        CourseUiState(
            isDataLoading = true,
            isPageEnded = false,
            courseFetchedResult = FetchResultUi.Loading()
        )
    )

    val courseFetchedState: StateFlow<CourseUiState> = _courseFetchedState
        .onStart { fetchCourses() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CourseUiState(
                isDataLoading = false,
                isPageEnded = false,
                courseFetchedResult = FetchResultUi.Loading()
            )
        )

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