package org.gulnazidr.dviz_excursion.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import org.gulnazidr.dviz_excursion.R
import org.gulnazidr.dviz_excursion.presentation.components.AnimatedBorderCard
import org.gulnazidr.dviz_excursion.presentation.components.BlurryCircle
import org.gulnazidr.dviz_excursion.presentation.components.InputField
import org.gulnazidr.dviz_excursion.presentation.components.NavigationButton
import org.gulnazidr.dviz_excursion.presentation.components.TopAppBar

@Composable
fun LoginScreen(
    onBack: () -> Unit,
) {
    var name by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isRememberChecked by rememberSaveable { mutableStateOf(false) }

    val height = LocalWindowInfo.current.containerDpSize.height.value
    val width = LocalWindowInfo.current.containerDpSize.width.value

    AnimatedBorderCard(
        modifier = Modifier.fillMaxSize()  .zIndex(1f)
    ) {
        Box(
            modifier = Modifier.padding(top = (height*0.02).dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .zIndex(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TopAppBar(
                    onBack = onBack
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.login_text),
                    color = colorResource(R.color.super_dark_purple),
                    fontSize = 28.sp,
                    lineHeight = 39.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(40.dp))

                InputField(
                    value = name,
                    onValueChange = { name = it },
                    hint = stringResource(R.string.user_name_hint)
                )

                Spacer(modifier = Modifier.height(20.dp))

                InputField(
                    value = password,
                    onValueChange = { password = it },
                    hint = stringResource(R.string.password_hint),
                    isPasswordField = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isRememberChecked,
                        onCheckedChange = { isRememberChecked = it },
                        colors = CheckboxDefaults.colors(
                            uncheckedBorderColor = colorResource(R.color.main_purple),
                            checkedBorderColor = colorResource(R.color.main_purple),
                            checkedBoxColor = Color.White,
                            checkedCheckmarkColor = colorResource(R.color.main_purple)
                        )
                    )

                    Text(
                        text = stringResource(R.string.remember_me_text),
                        fontSize = 16.sp,
                        color = colorResource(R.color.super_dark_purple),
                        fontWeight = FontWeight.Normal
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                NavigationButton(
                    onBtnClick = {},
                    text = stringResource(R.string.login_text)
                )

                Spacer(modifier = Modifier.weight(1f))
            }

            BlurryCircle(
                offsetX = (width*0.1).dp,
                offsetY = (height*0.35).dp,
                size = 200.dp,
                color = colorResource(R.color.lighter_orange)
            )

            BlurryCircle(
                offsetX = (width*0.3).dp,
                offsetY = (height*0.02).dp,
                size = 250.dp,
                color = colorResource(R.color.lighter_main_purple)
            )

            BlurryCircle(
                offsetX = (width*0.4).dp,
                offsetY = (height*0.7).dp,
                size = 180.dp,
                color = colorResource(R.color.lighter_purple)
            )
        }
    }
}

@Preview
@Composable
private fun LoginScreenPrev() {
    LoginScreen(
        onBack = {}
    )
}