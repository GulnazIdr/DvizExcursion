package org.example.project.feature.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.aakira.napier.Napier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.network.ktor.FetchCoursesUseCase
import org.example.project.feature.main.presentation.models.CourseUi

class SearchViewModel (
    private val fetchCoursesUseCase: FetchCoursesUseCase
): ViewModel() {
    private val _searchedCourseState = MutableStateFlow(
        SearchUiState(false, emptyList())
    )
    val searchedCourseState: StateFlow<SearchUiState> = _searchedCourseState

    private val _searchValues = MutableStateFlow("")
    val searchValue: StateFlow<String> = _searchValues

    fun onSearch(char: String) {
        _searchValues.value = char.lowercase()
        _searchedCourseState.update { state ->
            state.copy(
                isLoading = true
            )
        }
        filterCourses()
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun filterCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            _searchValues
                .debounce(1500)
                .distinctUntilChanged()
                .flatMapLatest { value ->
                    flowOf(value)
                }
                .collect { value ->
                    if (value.isEmpty()) {
                        _searchedCourseState.update { state ->
                            state.copy(
                                isLoading = false,
                                courseList = emptyList()
                            )
                        }
                        return@collect
                    }
                    _searchedCourseState.update { state ->
                        state.copy(
                            isLoading = false,
                            courseList = fetchCoursesUseCase.courseList.value
                                .filter { course ->
                                    course.title.lowercase().contains(value) ||
                                            course.description.lowercase().contains(value)
                                }
                        )
                    }
                }
        }
    }
}