package org.example.project.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.user_email_hint
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
                    color = MaterialTheme.colorScheme.onPrimary,
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
                BasicTextField(
                    value = value,
                    onValueChange = { onValueChange(it) },
                    modifier = Modifier
                        .padding(start = 15.dp),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    singleLine = true,
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
                            contentDescription = "password visibility off"
                        )
                    }
                }
            }

            if (value.isEmpty())
                Text(
                    text = hint,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
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
        hint = stringResource(Res.string.user_email_hint)
    )
}