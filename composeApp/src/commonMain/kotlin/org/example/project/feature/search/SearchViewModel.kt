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
import kotlinx.coroutines.launch
import org.example.project.core.network.ktor.FetchCoursesUseCase
import org.example.project.feature.main.presentation.models.CourseUi

class SearchViewModel (
    private val fetchCoursesUseCase: FetchCoursesUseCase
): ViewModel() {
    private val _isSearching = mutableStateOf(false)
    val isSearching = _isSearching
    private val _searchedCourseState = MutableStateFlow<List<CourseUi>>(emptyList())
    val searchedCourseState: StateFlow<List<CourseUi>> = _searchedCourseState

    private val _searchValues = MutableStateFlow("")
    val searchValue: StateFlow<String> = _searchValues

    fun onSearch(char: String){
        _isSearching.value = char.isNotEmpty()
        _searchValues.value = char.lowercase()

        filterCourses()
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun filterCourses(){
        viewModelScope.launch(Dispatchers.IO){
            _searchValues
                .debounce(1500)
                .distinctUntilChanged()
                .flatMapLatest{ value ->
                    flowOf(value)
                }
                .collect { value ->
                    _searchedCourseState.value = fetchCoursesUseCase.courseList.value
                        .filter { course ->
                            course.title.lowercase().contains(value) ||
                                    course.description.lowercase().contains(value)
                        }
                    _isSearching.value = false
                    if (value.isEmpty()) {
                        _searchedCourseState.value = emptyList()

                    }
        }

        }

    }
}