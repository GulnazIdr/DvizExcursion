package org.example.project.feature.main.presentation

import androidx.compose.runtime.mutableStateOf
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
import org.example.project.feature.main.presentation.models.CourseUi
import org.example.project.feature.main.presentation.result.FetchResultUi

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class CourseViewModel(
    private val fetchCoursesUseCase: FetchCoursesUseCase
): ViewModel() {
    private var fetchJob: Job? = null
    private val _isDataLoading = mutableStateOf(true)
    val isDataLoading = _isDataLoading

    private val _isPageEnded = mutableStateOf(false)
    val isPageEnded =_isPageEnded
    private val _courseFetchedResult = MutableStateFlow<FetchResultUi<List<CourseUi>>>(
        FetchResultUi.Loading()
    )
    val courseFetchedResult : StateFlow<FetchResultUi<List<CourseUi>>> = _courseFetchedResult
        .onStart { fetchCourses() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = FetchResultUi.Loading()
        )

    fun fetchCourses(){
        fetchJob = viewModelScope.launch {
            fetchCoursesUseCase(
                courseFetchedResult = _courseFetchedResult,
                isPageEnded = _isPageEnded,
                isDataLoading = _isDataLoading
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        fetchJob?.cancel()
        fetchJob = null
    }
}