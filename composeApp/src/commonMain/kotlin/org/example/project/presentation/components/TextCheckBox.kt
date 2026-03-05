package org.example.project.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TextCheckBox(
    text: String,
) {
    var isRememberChecked by rememberSaveable { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isRememberChecked,
            onCheckedChange = { isRememberChecked = it },
            colors = CheckboxDefaults.colors(
                uncheckedBorderColor = MaterialTheme.colorScheme.onSecondary,
                checkedBorderColor = MaterialTheme.colorScheme.onSecondary,
                checkedBoxColor = MaterialTheme.colorScheme.background,
                checkedCheckmarkColor = MaterialTheme.colorScheme.onSecondary,
            )
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        )
    }
}