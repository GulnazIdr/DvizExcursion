package org.gulnazidr.stepik.feature.profile.presentation.models

import org.gulnazidr.stepik.core.designsystem.ui_logic.result.FetchResultUi
import org.gulnazidr.stepik.feature.auth.presentation.models.UserUi

data class ProfileFetchUiState(
    val isRefreshing: Boolean,
    val userFetchedState: FetchResultUi<UserUi>
)
