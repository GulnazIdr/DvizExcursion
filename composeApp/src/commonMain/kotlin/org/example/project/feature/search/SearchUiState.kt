package org.example.project.feature.search

import org.example.project.feature.main.presentation.models.CourseUi

data class SearchUiState(
    val isLoading: Boolean,
    val courseList: List<CourseUi>
)
