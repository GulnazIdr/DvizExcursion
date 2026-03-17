package org.example.project.core.designsystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import stepik.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.stringResource
import stepik.composeapp.generated.resources.course_free_text

@Composable
fun PriceText(
    price: Double,
    priceColor: Color
){
    Text(
        text =
            if (price == 0.0)
                stringResource(Res.string.course_free_text)
            else "$price ₽",
        style = MaterialTheme.typography.bodyMedium.copy(
            color =
                if (price == 0.0)
                    MaterialTheme.colorScheme.onPrimaryFixed
                else priceColor
        ),
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(start = 12.dp)
    )
}