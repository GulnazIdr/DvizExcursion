package org.gulnazidr.dviz_excursion.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import org.gulnazidr.dviz_excursion.R
import org.gulnazidr.dviz_excursion.presentation.components.NavigationButton

@Composable
fun OnBoarding(
    navigateToLogin: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SubcomposeAsyncImage(
            model = "https://i.pinimg.com/474x/72/82/b1/7282b11b0f4b2ca9f03b622556ce43ff.jpg?nii=t",
            contentDescription = "on boarding image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .graphicsLayer { alpha = 0.998F }
                .drawWithContent {
                    val colors = listOf(Color.Black, Color.Transparent)
                    drawContent()
                    drawRect(
                        brush = Brush.verticalGradient(colors),
                        blendMode = BlendMode.DstIn
                    )
                },
            loading = {
                Box {

                }
            },
            error = {
                Image(
                    painter = painterResource(R.drawable.on_boarding),
                    contentDescription = "on boarding image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer { alpha = 0.998F }
                        .drawWithContent {
                            val colors = listOf(Color.Black, Color.Transparent)
                            drawContent()
                            drawRect(
                                brush = Brush.verticalGradient(colors),
                                blendMode = BlendMode.DstIn
                            )
                        }
                )
                Log.e(
                    "loading events error",
                    "${it.result.throwable.message} " + "${it.result.throwable.cause}"
                )
            }
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 50.dp)
            ) {
                Text(
                    text = stringResource(R.string.on_boarding_title),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 28.sp,
                    lineHeight = 39.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = stringResource(R.string.on_boarding_descr),
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            NavigationButton(
                onBtnClick = navigateToLogin,
                text = stringResource(R.string.get_started_text),
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }

    }
}

@Preview
@Composable
private fun OnBoardingPrev() {
    OnBoarding(
        navigateToLogin = {}
    )
}