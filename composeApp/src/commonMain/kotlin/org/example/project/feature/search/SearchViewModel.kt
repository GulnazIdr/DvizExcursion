package org.example.project.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import org.example.project.core.common.result.FetchCourseResult
import org.example.project.core.designsystem.ui_logic.mapper.CourseToCourseDetailUiMapper
import org.example.project.core.domain.FetchCoursesUseCase
import org.example.project.feature.course_catalog.presentation.models.CourseDetailUi

class SearchViewModel(
    private val fetchCoursesUseCase: FetchCoursesUseCase,
    private val courseUiMapper: CourseToCourseDetailUiMapper
) : ViewModel() {
    private val _searchedCourseState = MutableStateFlow(
        SearchUiState(isLoading = false, isRefreshing = false, courseList = emptyList())
    )
    val searchedCourseState: StateFlow<SearchUiState> = _searchedCourseState

    private val _searchValues = MutableStateFlow("")
    val searchValue: StateFlow<String> = _searchValues

    fun refresh(value: String) {
        _searchedCourseState.update { state ->
            state.copy(
                isRefreshing = true
            )
        }
        onSearch(value)
    }

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
        viewModelScope.launch {
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
                                courseList = emptyList(),
                                isRefreshing = false
                            )
                        }
                        return@collect
                    }

                    getFilteredCourseList(value)
                }
        }
    }

    private fun getFilteredCourseList(value: String) {
        _searchedCourseState.update { state ->
            state.copy(
                isLoading = false,
                courseList = transformFetchCourseResult(value),
                isRefreshing = false
            )
        }
    }

    private fun transformFetchCourseResult(value: String): List<CourseDetailUi> {
        return when (val res = fetchCoursesUseCase.getCourseFetchResult()) {
            is FetchCourseResult.Error -> {
                emptyList()
            }

            is FetchCourseResult.Success -> {
                res.stepikData.successData
                    .map(courseUiMapper::map)
                    .filter { course ->
                        course.courseUi.title.lowercase().contains(value) ||
                                course.courseUi.description.lowercase().contains(value)
                    }
            }

            is FetchCourseResult.Cache -> {
                res.cacheData.successData.map(courseUiMapper::map)
            }

            null -> {
                emptyList()
            }
        }
    }
}


