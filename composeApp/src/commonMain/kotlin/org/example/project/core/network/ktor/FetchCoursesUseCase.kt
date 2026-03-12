package org.example.project.core.network.ktor

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import org.example.project.core.common.result.FetchResult
import org.example.project.core.common.result.NetworkError
import org.example.project.core.designsystem.asUiText
import org.example.project.feature.main.domain.Stepik
import org.example.project.feature.main.domain.StepikApi
import org.example.project.feature.main.presentation.mappers.toCourseUi
import org.example.project.feature.main.presentation.models.CourseUi
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
        courseFetchedResult: MutableStateFlow<FetchResultUi<List<CourseUi>>>,
        isPageEnded: MutableState<Boolean>,
        isDataLoading : MutableState<Boolean>,
    ){
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
                            invoke(courseFetchedResult, isPageEnded, isDataLoading)
                            return@withContext
                        }

                        courseFetchedResult.value = FetchResultUi.Success(
                            data = _courseList.value
                        )

                        if (_courseList.value.size < 20 && isActive) {
                            invoke(courseFetchedResult, isPageEnded, isDataLoading)
                            return@withContext
                        }

                        isDataLoading.value = false

                    } else
                        isPageEnded.value = true
                }
            }
            is FetchResult.ErrorRes<NetworkError> -> {
                withContext(Dispatchers.Main) {
                    courseFetchedResult.value = FetchResultUi.Error(
                        fetchResult.error.asUiText()
                    )
                }
            }
        }
    }
}