package org.gulnazidr.stepik.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.close
import org.jetbrains.compose.resources.painterResource

@Composable
fun ErrorDialog(
    errorMessage: String,
    onRetry: () -> Unit,
    onClose: () -> Unit
){
    Dialog(onDismissRequest = { onClose() }) {
        Box(
            modifier = Modifier.fillMaxWidth().height((getWindowHeight()*0.25).dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(20.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = {onClose()},
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.close),
                            contentDescription = "close icon",
                            Modifier.size(25.dp)
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(20.dp).align(Alignment.CenterHorizontally),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = errorMessage,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.background
                        ),

                        )

                    Spacer(modifier = Modifier.height(10.dp))

                    RetryBtn(
                        onRetry = onRetry
                    )
                }
            }
        }
    }
}