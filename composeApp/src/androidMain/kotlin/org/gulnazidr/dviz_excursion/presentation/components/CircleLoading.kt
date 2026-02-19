package org.gulnazidr.dviz_excursion.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.gulnazidr.dviz_excursion.R

@Composable
fun CircleLoading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ){
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .height(40.dp)
                .width(40.dp),
            color = colorResource(R.color.main_purple),
            trackColor = Color.Black
        )
    }
}

@Preview
@Composable
private fun CircleLoadingPrev() {
    CircleLoading()
}