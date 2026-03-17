package org.example.project.feature.course_detail

import org.example.project.feature.course_catalog.presentation.models.CourseDetailUi
import org.example.project.feature.course_catalog.presentation.result.FetchResultUi

data class CourseDetailUiState(
    val isRefreshing: Boolean,
    val courseState: FetchResultUi<CourseDetailUi>
)
