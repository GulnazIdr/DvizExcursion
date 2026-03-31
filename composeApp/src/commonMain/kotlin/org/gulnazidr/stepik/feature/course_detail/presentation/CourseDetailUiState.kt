package org.gulnazidr.stepik.feature.course_detail.presentation

import org.gulnazidr.stepik.core.designsystem.ui_logic.model.CourseDetailUi
import org.gulnazidr.stepik.core.designsystem.ui_logic.result.FetchResultUi

data class CourseDetailUiState(
    val isRefreshing: Boolean,
    val courseState: FetchResultUi<CourseDetailUi>
)
