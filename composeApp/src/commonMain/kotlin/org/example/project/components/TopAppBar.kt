package org.example.project.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.arrow

@Composable
fun TopAppBar(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        CustomIconButton(
            idDrawable = Res.drawable.arrow,
            modifier = Modifier
                .clickable(onClick =onBack)
                .align(Alignment.TopStart),
            backgroundColor = Color.White,
            size = 44.dp,
            iconSize = 22.dp
        )
    }
}

@Preview
@Composable
private fun TopAppBarPrev() {
    TopAppBar(
        onBack = {}
    )
}