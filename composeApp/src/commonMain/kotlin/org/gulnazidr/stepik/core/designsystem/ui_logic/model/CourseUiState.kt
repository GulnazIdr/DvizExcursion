package org.gulnazidr.stepik.core.designsystem.ui_logic.model

import org.gulnazidr.stepik.core.designsystem.ui_logic.result.FetchResultUi

data class CourseUiState(
    val isDataLoading: Boolean,
    val isPageEnded: Boolean,
    val isRefreshing: Boolean,
    val courseFetchedResult: FetchResultUi<List<CourseDetailUi>>
)