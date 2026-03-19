package org.example.project.feature.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.core.designsystem.components.InputField
import org.example.project.feature.profile.components.ContactInfoRow
import org.example.project.feature.profile.components.ProfileImage
import org.example.project.feature.profile.components.ProfileInfoCard
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
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
    var isEditEnabled by rememberSaveable { mutableStateOf(false) }
    val profileUiState = profileViewModel.profileUiState.collectAsStateWithLifecycle().value

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
                onLogout()
            })
        )

        Spacer(modifier = Modifier.height(20.dp))

        ProfileImage(
            imageUrl = "",
            onEdit = {
                isEditEnabled = !isEditEnabled
                if (!isEditEnabled) {
                    profileViewModel.updateUser()
                }
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
    }
}