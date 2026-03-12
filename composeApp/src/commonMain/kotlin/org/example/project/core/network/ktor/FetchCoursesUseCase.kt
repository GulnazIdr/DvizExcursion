package org.example.project.core.network.ktor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import org.example.project.core.common.result.FetchResult
import org.example.project.core.common.result.NetworkError
import org.example.project.core.designsystem.asUiText
import org.example.project.feature.main.domain.Stepik
import org.example.project.feature.main.domain.StepikApi
import org.example.project.feature.main.presentation.mappers.toCourseUi
import org.example.project.feature.main.presentation.models.CourseUi
import org.example.project.feature.main.presentation.models.CourseUiState
import org.example.project.feature.main.presentation.result.FetchResultUi

class FetchCoursesUseCase(
    private val stepikApi: StepikApi
) {
    private var currentPage = 1
    private val _courseList: MutableStateFlow<List<CourseUi>> = MutableStateFlow(
        value = emptyList()
    )

    val courseList: StateFlow<List<CourseUi>> = _courseList

    suspend operator fun invoke(
        courseFetchedState: MutableStateFlow<CourseUiState>
    ) {
        courseFetchedState.update { state ->
            state.copy(
                isDataLoading = true
            )
        }

        when (val fetchResult = withContext(Dispatchers.IO) {
            stepikApi.getCourses(currentPage)
        }) {
            is FetchResult.Success<Stepik> -> {
                if (fetchResult.successData.pageInfo.hasNext) {
                    currentPage++
                    _courseList.value += fetchResult.successData.courses.map { it.toCourseUi() }

                    if (_courseList.value.isEmpty()) {
                        invoke(courseFetchedState)
                        return
                    }

                    courseFetchedState.update { state ->
                        state.copy(
                            courseFetchedResult = FetchResultUi.Success(data = _courseList.value),
                            isDataLoading = false
                        )
                    }

                    if (_courseList.value.size < 20) {
                        invoke(courseFetchedState)
                        return
                    }

                } else {
                    courseFetchedState.update { state ->
                        state.copy(
                            isPageEnded = true,
                            isDataLoading = false
                        )
                    }
                }
            }

            is FetchResult.ErrorRes<NetworkError> -> {
                withContext(Dispatchers.Main) {
                    courseFetchedState.update { state ->
                        state.copy(
                            courseFetchedResult = FetchResultUi.Error(
                                fetchResult.error.asUiText()
                            ),
                            isDataLoading = false
                        )
                    }
                }
            }
        }
    }
}