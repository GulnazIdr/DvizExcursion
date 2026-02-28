package org.example.project.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.get_started_text
import org.jetbrains.compose.resources.stringResource

@Composable
fun NavigationButton(
    onBtnClick: () -> Unit,
    text: String,
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onBtnClick,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
     ,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        enabled = isEnabled
    ) {
        Text(
            text = text
        )
    }
}

@Preview
@Composable
private fun NavigationButtonPrev() {
    NavigationButton(
        onBtnClick = {},
        text = stringResource(Res.string.get_started_text),
        isEnabled = false
    )
}