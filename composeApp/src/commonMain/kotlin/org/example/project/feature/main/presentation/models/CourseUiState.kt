package org.example.project.feature.main.presentation.models

import kotlinx.coroutines.flow.MutableStateFlow
import org.example.project.feature.main.presentation.result.FetchResultUi

data class CourseUiState(
    val isDataLoading: Boolean,
    val isPageEnded: Boolean,
    val courseFetchedResult: FetchResultUi<List<CourseUi>>
)