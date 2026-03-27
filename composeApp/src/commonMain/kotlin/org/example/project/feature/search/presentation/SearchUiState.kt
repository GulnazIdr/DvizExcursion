package org.example.project.feature.search.presentation

import org.example.project.core.designsystem.ui_logic.model.CourseDetailUi

data class SearchUiState(
    val isLoading: Boolean,
    val isRefreshing: Boolean,
    val courseList: List<CourseDetailUi>
)
