//package org.example.org.gulnazidr.stepik.feature.auth.presentation.components
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.window.Dialog
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import org.example.org.gulnazidr.stepik.core.designsystem.components.InputField
//import org.example.org.gulnazidr.stepik.core.designsystem.components.NavigationLoadingButton
//import org.example.org.gulnazidr.stepik.feature.auth.presentation.AuthViewModel
//import org.gulnazidr.dviz_excursion.R
//import org.koin.androidx.compose.koinViewModel
//import stepik.composeapp.generated.resources.Res
//import stepik.composeapp.generated.resources.login_dialog_cancel
//
//@Composable
//fun DialogStepikLoginInfo(
//    navigateToMain: () -> Unit,
//    isCanceled: Boolean,
//    onCancelled: (Boolean) -> Unit
//) {
//    val viewModel: AuthViewModel = koinViewModel<AuthViewModel>()
//    val tokenUiState by viewModel.tokenUiState.collectAsStateWithLifecycle()
//
//    LaunchedEffect(tokenUiState) {
//        if (tokenUiState.isSaved){
//            navigateToMain()
//        }
//    }
//
//   // if (!isCanceled)
//    Dialog(onDismissRequest = { onCancelled(!isCanceled) }) {
//        Column(
//            modifier = Modifier
//                .background(
//                    color = MaterialTheme.colorScheme.onSecondaryContainer,
//                    shape = RoundedCornerShape(10.dp)
//                )
//                .padding(20.dp)
//        ) {
//            Column(
//                modifier = Modifier
//                    .padding(20.dp)
//                    .align(Alignment.CenterHorizontally),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//
//                Text(
//                    text = stringResource(R.string.login_paste_url_text),
//                    style = MaterialTheme.typography.bodyMedium.copy(
//                        color = MaterialTheme.colorScheme.background
//                    ),
//                    textAlign = TextAlign.Center
//                )
//
//                Spacer(modifier = Modifier.height(10.dp))
//
//                InputField(
//                    value = tokenUiState.input,
//                    onValueChange = {
//                        viewModel.onUrlChanged(it)
//                    },
//                    textColor = MaterialTheme.colorScheme.background
//                )
//
//                if (tokenUiState.errorMessage.isNotEmpty()) {
//                    Spacer(modifier = Modifier.height(7.dp))
//
//                    Text(
//                        text = tokenUiState.errorMessage,
//                        style = MaterialTheme.typography.bodyMedium.copy(
//                            color = MaterialTheme.colorScheme.error
//                        )
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                NavigationLoadingButton(
//                    initialText = "Okay",
//                    onAction = {
//                       // viewModel.openLoginPage()
//
//                               },
//                    isLoading = tokenUiState.isLoading
//                )
//
//                Spacer(modifier = Modifier.height(25.dp))
//
//                Text(
//                    text = org.jetbrains.compose.resources.stringResource(Res.string.login_dialog_cancel),
//                    style = MaterialTheme.typography.bodySmall.copy(
//                        color = MaterialTheme.colorScheme.background
//                    ),
//                    modifier = Modifier.clickable(onClick = { onCancelled(true) })
//                )
//            }
//        }
//
//    }
//}