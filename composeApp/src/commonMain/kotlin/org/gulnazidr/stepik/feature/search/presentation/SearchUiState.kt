package org.gulnazidr.stepik.feature.search.presentation

import org.gulnazidr.stepik.core.designsystem.ui_logic.model.CourseDetailUi

data class SearchUiState(
    val isLoading: Boolean,
    val isRefreshing: Boolean,
    val courseList: List<CourseDetailUi>
)
