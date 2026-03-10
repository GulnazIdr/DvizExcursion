package org.example.project.feature.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.arrow
import dvizexcursion.composeapp.generated.resources.login_text
import dvizexcursion.composeapp.generated.resources.registration_text
import org.example.project.core.designsystem.components.CustomIconButton
import org.example.project.feature.main.presentation.components.SearchBar
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchTopAppBar(
    onBack: () -> Unit,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomIconButton(
            idDrawable = Res.drawable.arrow,
            modifier = Modifier
                .clickable(onClick = onBack),
            size = 44.dp,
            iconSize = 22.dp
        )

        Spacer(modifier = Modifier.width(10.dp))

        SearchBar(
            onValueChanged = onValueChanged
        )
    }
}

@Preview
@Composable
private fun SearchTopAppBarPrev() {
    SearchTopAppBar(
        onBack = {},
        onValueChanged = {}
    )
}