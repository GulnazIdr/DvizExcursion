package org.example.project.feature.course_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.project.core.common.result.FetchResult
import org.example.project.core.common.result.NetworkError
import org.example.project.core.designsystem.asUiText
import org.example.project.feature.main.domain.StepikApi
import org.example.project.feature.main.domain.StepikDetailed
import org.example.project.feature.main.presentation.mappers.toCourseDetailUi
import org.example.project.feature.main.presentation.models.CourseDetailUi
import org.example.project.feature.main.presentation.result.FetchResultUi

class CourseDetailViewModel (
    private val stepikApi: StepikApi
): ViewModel() {

    private val _currentCourseRes = MutableStateFlow<FetchResultUi<CourseDetailUi>>(
        FetchResultUi.Loading())
    val currentCourseRes: StateFlow<FetchResultUi<CourseDetailUi>> = _currentCourseRes.asStateFlow()
    private var fetchSpecificCourseJob: Job? = null

    fun getCourseById(id: Int){
        fetchSpecificCourseJob?.cancel()

        fetchSpecificCourseJob = viewModelScope.launch(Dispatchers.IO) {
            when(val res = stepikApi.getCourseById(id)){
                is FetchResult.Success<StepikDetailed> -> {
                    withContext(Dispatchers.Main) {
                        _currentCourseRes.value = FetchResultUi.Success(
                            data = res.successData.courses.first().toCourseDetailUi()
                        )
                    }
                }
                is FetchResult.ErrorRes<NetworkError> -> {
                    withContext(Dispatchers.Main) {
                        _currentCourseRes.value = FetchResultUi.Error(
                            res.error.asUiText()
                        )
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        fetchSpecificCourseJob?.cancel()
        fetchSpecificCourseJob = null
    }
}