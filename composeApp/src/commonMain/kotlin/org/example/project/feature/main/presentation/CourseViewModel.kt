package org.example.project.feature.main.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.project.core.common.result.FetchResult
import org.example.project.core.common.result.NetworkError
import org.example.project.core.designsystem.asUiText
import org.example.project.feature.main.domain.Stepik
import org.example.project.feature.main.domain.StepikApi
import org.example.project.feature.main.domain.StepikDetailed
import org.example.project.feature.main.presentation.mappers.toCourseDetailUi
import org.example.project.feature.main.presentation.mappers.toCourseUi
import org.example.project.feature.main.presentation.models.CourseDetailUi
import org.example.project.feature.main.presentation.models.CourseUi
import org.example.project.feature.main.presentation.result.FetchResultUi

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class CourseViewModel(
    private val stepikApi: StepikApi
): ViewModel() {
    private var fetchJob: Job? = null
    private var fetchSpecificCourseJob: Job? = null
    private val _isDataLoading = mutableStateOf(true)
    val isDataLoading = _isDataLoading
    private val _isSearching = mutableStateOf(false)
    val isSearching =_isSearching
    private var currentPage = 1

    private val _isPageEnded = mutableStateOf(false)
    val isPageEnded =_isPageEnded
    private val _courseList: MutableStateFlow<List<CourseUi>> = MutableStateFlow(
        value = emptyList()
    )
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

    private val _fetchedCourseResult = MutableStateFlow<FetchResultUi<
            CourseDetailUi>>(FetchResultUi.Loading())
    val fetchedCourseResult = _fetchedCourseResult.asStateFlow()

    private val _searchedCourseState = MutableStateFlow<List<CourseUi>>(emptyList())
    val searchedCourseState: StateFlow<List<CourseUi>> = _searchedCourseState

    private val _searchValues = MutableStateFlow("")

    init {
        viewModelScope.launch(Dispatchers.IO){
            _searchValues
                .filter { value ->
                    _searchedCourseState.value = emptyList()
                    return@filter value.isNotEmpty()
                }
                .debounce(1500)
                .distinctUntilChanged()
                .flatMapLatest<String, String> { value ->
                    flowOf(value)
                }
                .collect {
                    filterCourses(it)
                }
        }
    }

    fun fetchCourses(){
        fetchJob?.cancel()
        fetchJob =  viewModelScope.launch {
            _isDataLoading.value = true
            when(val fetchResult = stepikApi.getCourses(currentPage)){
                is FetchResult.Success<Stepik> -> {
                    withContext(Dispatchers.Main) {
                        if (!isActive) {
                            return@withContext
                        }

                        if (fetchResult.successData.pageInfo.hasNext) {
                            currentPage++
                            _courseList.value += fetchResult.successData.courses.map { it.toCourseUi() }

                            if (_courseList.value.isEmpty() && isActive) {
                                fetchCourses()
                                return@withContext
                            }

                            _courseFetchedResult.value = FetchResultUi.Success(
                                data = _courseList.value
                            )

                            if (_courseList.value.size < 20 && isActive) {
                                fetchCourses()
                                return@withContext
                            }

                            _isDataLoading.value = false

                        } else
                            _isPageEnded.value = true
                    }
                }
                is FetchResult.ErrorRes<NetworkError> -> {
                    withContext(Dispatchers.Main) {
                        _courseFetchedResult.value = FetchResultUi.Error(
                            fetchResult.error.asUiText()
                        )
                    }
                }
            }
        }
    }

    fun onSearch(char: String){
        _isSearching.value = char.isNotEmpty()
        _searchValues.value = char.lowercase()
    }

    fun setCurrentCourseId(id: Int){
        viewModelScope.launch {
            getCourseById(id)
        }
    }

    fun getCourseById(id: Int){
        fetchSpecificCourseJob?.cancel()

        fetchSpecificCourseJob = viewModelScope.launch(Dispatchers.IO) {
            when(val res = stepikApi.getCourseById(id)){
                is FetchResult.Success<StepikDetailed> -> {
                    withContext(Dispatchers.Main) {
                        _fetchedCourseResult.value = FetchResultUi.Success(
                            data = res.successData.courses.first().toCourseDetailUi()
                        )
                    }
                }
                is FetchResult.ErrorRes<NetworkError> -> {
                    withContext(Dispatchers.Main) {
                        _fetchedCourseResult.value = FetchResultUi.Error(
                            res.error.asUiText()
                        )
                    }
                }
            }
        }
    }

    fun clearSearchState(){
        _searchedCourseState.value = emptyList()
    }

    private fun filterCourses(value: String){
        _searchedCourseState.value = _courseList.value
            .filter { course ->
                course.title.lowercase().contains(value) ||
                        course.description.lowercase().contains(value)
            }

        if (_searchedCourseState.value.isEmpty())
            _isSearching.value = false
    }

    override fun onCleared() {
        super.onCleared()
        fetchJob?.cancel()
        fetchSpecificCourseJob?.cancel()
        fetchJob = null
        fetchSpecificCourseJob = null
    }
}