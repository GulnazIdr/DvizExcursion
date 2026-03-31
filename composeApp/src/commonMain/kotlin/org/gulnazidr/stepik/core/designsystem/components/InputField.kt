package org.gulnazidr.stepik.core.designsystem.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.visibiliy_off
import stepik.composeapp.generated.resources.visibiliy_on

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String = "",
    errorText: String = "",
    isEnabled: Boolean = true,
    isPasswordField: Boolean = false,
    textColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
) {
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    Column{
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(51.dp)
                .border(
                    color = MaterialTheme.colorScheme.onSecondary,
                    width = 1.5.dp,
                    shape = RoundedCornerShape(15.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomTextField(
                    value = value,
                    onValueChange = { if (isEnabled) onValueChange(it) },
                    visualTransformation =
                        if (isPasswordField && !isPasswordVisible) PasswordVisualTransformation()
                        else VisualTransformation.None,
                    isEnabled = isEnabled,
                    textColor = textColor
                )

                if (isPasswordField) {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            painter = painterResource(
                                if (isPasswordVisible) Res.drawable.visibiliy_on
                                else Res.drawable.visibiliy_off
                            ),
                            modifier = Modifier.size(23.dp),
                            contentDescription = "password visibility off",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }

            if (value.isEmpty())
                Text(
                    text = hint,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSecondary
                    ),
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .align(Alignment.CenterStart)
                )
        }

        if (errorText.isNotEmpty()) {
            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = errorText,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.error
                )
            )
        }
    }

}

@Preview
@Composable
private fun InputFieldPrev() {
    InputField(
        value = "",
        onValueChange = {},
        hint = "hint",
        errorText = ""
    )
}