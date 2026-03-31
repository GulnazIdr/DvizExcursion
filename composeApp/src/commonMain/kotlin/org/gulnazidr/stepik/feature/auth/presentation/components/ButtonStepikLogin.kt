package org.gulnazidr.stepik.feature.auth.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.stepik_logo

@Composable
fun ButtonStepikLogin(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            MaterialTheme.colorScheme.outline
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(4.dp, MaterialTheme.colorScheme.onPrimary)
    ) {
        Icon(
            painter = painterResource(Res.drawable.stepik_logo),
            contentDescription = "stepik logo icon",
            modifier = Modifier.size(40.dp),
            tint = Color.Unspecified,
        )
    }
}