package org.example.project.core.designsystem.ui_logic.model

import org.example.project.feature.course_catalog.presentation.models.CourseDetailUi
import org.example.project.feature.course_catalog.presentation.result.FetchResultUi

data class CourseUiState(
    val isDataLoading: Boolean,
    val isPageEnded: Boolean,
    val isRefreshing: Boolean,
    val courseFetchedResult: FetchResultUi<List<CourseDetailUi>>
)