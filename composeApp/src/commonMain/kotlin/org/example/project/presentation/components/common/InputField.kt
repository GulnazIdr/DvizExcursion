package org.example.project.presentation.components.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.user_name_hint
import dvizexcursion.composeapp.generated.resources.visibiliy_off
import dvizexcursion.composeapp.generated.resources.visibiliy_on
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    isPasswordField: Boolean = false
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
                    onValueChange = { onValueChange(it) },
                    visualTransformation =
                        if (isPasswordField && !isPasswordVisible) PasswordVisualTransformation()
                        else VisualTransformation.None
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
    }

}

@Preview
@Composable
private fun InputFieldPrev() {
    InputField(
        value = "",
        onValueChange = {},
        hint = stringResource(Res.string.user_name_hint)
    )
}