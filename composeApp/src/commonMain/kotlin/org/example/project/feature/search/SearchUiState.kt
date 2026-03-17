package org.example.project.feature.search

import org.example.project.feature.course_catalog.presentation.models.CourseDetailUi

data class SearchUiState(
    val isLoading: Boolean,
    val isRefreshing: Boolean,
    val courseList: List<CourseDetailUi>
)
