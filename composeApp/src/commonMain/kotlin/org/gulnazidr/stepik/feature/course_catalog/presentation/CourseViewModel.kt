package org.gulnazidr.stepik.feature.course_catalog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.gulnazidr.stepik.core.common.result.FetchDataResult
import org.gulnazidr.stepik.core.common.result.ServerNetworkError
import org.gulnazidr.stepik.core.common.result.UserNetworkError
import org.gulnazidr.stepik.core.designsystem.ui_logic.mapper.CourseToCourseDetailUiMapper
import org.gulnazidr.stepik.core.designsystem.ui_logic.mapper.UserToUserUiMapper
import org.gulnazidr.stepik.core.designsystem.ui_logic.mapper.asUiText
import org.gulnazidr.stepik.core.designsystem.ui_logic.model.CourseUiState
import org.gulnazidr.stepik.core.designsystem.ui_logic.result.FetchResultUi
import org.gulnazidr.stepik.core.designsystem.ui_logic.result.FetchResultUi.Error
import org.gulnazidr.stepik.core.designsystem.ui_logic.result.FetchResultUi.Success
import org.gulnazidr.stepik.core.domain.courses.result.CourseSuccessResult
import org.gulnazidr.stepik.feature.course_catalog.domain.FetchAuthorsUseCase
import org.gulnazidr.stepik.feature.course_catalog.domain.FetchCoursesUseCase
import org.gulnazidr.stepik.feature.profile.domain.LogoutUseCase

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class CourseViewModel(
    private val fetchCoursesUseCase: FetchCoursesUseCase,
    private val courseToCourseDetailUiMapper: CourseToCourseDetailUiMapper,
    private val fetchAuthorsUseCase: FetchAuthorsUseCase,
    private val userUiMapper: UserToUserUiMapper,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private var fetchJob: Job? = null

    private val _logoutEvent = MutableSharedFlow<Boolean>()
    val logoutEvent = _logoutEvent.asSharedFlow()

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
                                isPageEnded = true
                            )
                        }
                    }else{
                        _courseFetchedState.update { state ->
                            state.copy(
                                isPageEnded = false
                            )
                        }
                    }

                    val successData = result.data.successData.map(
                        courseToCourseDetailUiMapper::map
                    )

                    _courseFetchedState.update { state ->
                        state.copy(
                            isDataLoading = false,
                            courseFetchedResult = Success(successData),
                            isRefreshing = false
                        )
                    }

//                    result.data.successData.forEach { course ->
//                        val authorList = fetchAuthorsUseCase.invoke(
//                            course.authorList.map { author -> author.id })
//
//                        _courseFetchedState.update { state ->
//                            state.copy(
//                                courseFetchedResult = Success(
//                                    successData.map { courseDetailUi ->
//                                        courseDetailUi.copy(
//                                            courseUi = courseDetailUi.courseUi.copy(
//                                                authorList = authorList.map {
//                                                    userUiMapper.map(it)
//                                                }
//                                            )
//                                        )
//                                    }
//                                )
//                            )
//                        }
//
//                   }
                }

                is FetchDataResult.Error -> {
                    if (result.error == ServerNetworkError.TOKEN_REFRESH){
                        _logoutEvent.emit(  logoutUseCase())
                    }

                    _courseFetchedState.update { state ->
                        state.copy(
                            courseFetchedResult = Error(
                                message =
                                    if (result.error is UserNetworkError) {
                                        result.error.asUiText()
                                    } else {
                                        UserNetworkError.UNKNOWN.asUiText()
                                    }
                            ),
                            isDataLoading = false,
                            isRefreshing = false,
                            isPageEnded = true
                        )
                    }
                }

                is FetchDataResult.Cache -> {
                    _courseFetchedState.update { state ->
                        state.copy(
                            courseFetchedResult = Error(
                                message =
                                    if (result.error is UserNetworkError) {
                                        result.error.asUiText()
                                    } else {
                                        UserNetworkError.UNKNOWN.asUiText()
                                    },
                                cacheData = courseToCourseDetailUiMapper.map(
                                    result.cacheData.successData
                                )
                            ),
                            isDataLoading = false,
                            isRefreshing = false,
                            isPageEnded = true
                        )
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        fetchJob?.cancel()
        fetchJob = null
    }
}