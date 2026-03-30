package org.gulnazidr.stepik.feature.profile.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.gulnazidr.stepik.core.common.di.UserSessionScope
import org.gulnazidr.stepik.core.designsystem.components.CircleLoading
import org.gulnazidr.stepik.core.designsystem.components.ErrorDialog
import org.gulnazidr.stepik.core.designsystem.components.InputField
import org.gulnazidr.stepik.feature.profile.presentation.components.ContactInfoRow
import org.gulnazidr.stepik.feature.profile.presentation.components.ProfileImage
import org.gulnazidr.stepik.feature.profile.presentation.components.ProfileInfoCard
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.getKoin
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.Koin
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.auth_email_hint
import stepik.composeapp.generated.resources.auth_phone_hint
import stepik.composeapp.generated.resources.auth_user_name_hint
import stepik.composeapp.generated.resources.email
import stepik.composeapp.generated.resources.phone
import stepik.composeapp.generated.resources.profile_info
import stepik.composeapp.generated.resources.profile_logout
import stepik.composeapp.generated.resources.profile_name

@Composable
fun ProfileScreen(
    onLogout: () -> Unit,
    profileViewModel: ProfileViewModel = koinViewModel<ProfileViewModel>(),
    modifier: Modifier = Modifier
) {
    var isDismissed by rememberSaveable { mutableStateOf(false) }
    val profileFetchState by profileViewModel.profileFetchedUiState.collectAsStateWithLifecycle()
    var isEditEnabled by rememberSaveable { mutableStateOf(false) }
    val profileUiState = profileViewModel.profileUiState.collectAsStateWithLifecycle().value

    val koin: Koin = getKoin()
    val profileLogoutEvent = profileViewModel.profileLogoutEvent
    LaunchedEffect(profileLogoutEvent) {
        profileLogoutEvent.collect { isLogoutSafe ->
            if (isLogoutSafe){
                koin.deleteScope(UserSessionScope.USER_SESSION_SCOPE.value)
                onLogout()
            }
        }
    }

    PullToRefreshBox(
        isRefreshing = profileFetchState.isRefreshing,
        onRefresh = { profileViewModel.retry() }
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(Res.string.profile_logout),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.error
                ),
                modifier = Modifier.clickable(onClick = {
                    profileViewModel.logout()
                })
            )

            Spacer(modifier = Modifier.height(20.dp))

            profileFetchState.userFetchedState.Display(
                onSuccess = { user ->
                    profileViewModel.setInitialUserData(user)

                    ProfileImage(
                        imageUrl = user.profileImg,
                        onEdit = {
                            isEditEnabled = !isEditEnabled
                        },
                        isEditEnabled = isEditEnabled,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    ProfileInfoCard(
                        title = stringResource(Res.string.profile_name)
                    ) {
                        InputField(
                            value = profileUiState.userName,
                            onValueChange = { profileViewModel.onNameChanged(it) },
                            hint = stringResource(Res.string.auth_user_name_hint),
                            errorText = profileUiState.nameError?.asString() ?: "",
                            isEnabled = isEditEnabled
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    ProfileInfoCard(
                        title = stringResource(Res.string.profile_info)
                    ) {
                        ContactInfoRow(
                            iconRes = Res.drawable.phone,
                            hint = stringResource(Res.string.auth_phone_hint),
                            value = profileUiState.phone,
                            onValueChange = { profileViewModel.onPhoneChanged(it) },
                            error = profileUiState.phoneError?.asString() ?: "",
                            isEnabled = isEditEnabled
                        )

                        Divider(modifier = Modifier.padding(vertical = 12.dp))

                        ContactInfoRow(
                            iconRes = Res.drawable.email,
                            hint = stringResource(Res.string.auth_email_hint),
                            value = profileUiState.email,
                            onValueChange = { profileViewModel.onEmailChanged(it) },
                            error = profileUiState.emailError?.asString() ?: "",
                            isEnabled = isEditEnabled
                        )
                    }
                },
                onError = { error ->
                    ErrorDialog(
                        errorMessage = error,
                        onRetry = { profileViewModel.retry() },
                        onClose = { isDismissed = true }
                    )
                },
                onLoading = {
                    CircleLoading()
                },
                isDismissed = isDismissed
            )
        }
    }
}