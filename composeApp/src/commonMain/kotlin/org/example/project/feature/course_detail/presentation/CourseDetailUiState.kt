package org.example.project.feature.course_detail.presentation

import org.example.project.feature.course_catalog.presentation.models.CourseDetailUi
import org.example.project.core.designsystem.ui_logic.result.FetchResultUi

data class CourseDetailUiState(
    val isRefreshing: Boolean,
    val courseState: FetchResultUi<CourseDetailUi>
)
