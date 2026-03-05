package org.example.project.presentation.onboarding.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.dog_bowl
import dvizexcursion.composeapp.generated.resources.dumbbells
import dvizexcursion.composeapp.generated.resources.game_console
import dvizexcursion.composeapp.generated.resources.hearts
import dvizexcursion.composeapp.generated.resources.science
import dvizexcursion.composeapp.generated.resources.scientific
import org.example.project.presentation.components.getWindowHeight
import org.example.project.presentation.auth.components.RedditLogo
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun CircleOfIcons(){
    Box(
        modifier = Modifier.size(((getWindowHeight() *(0.35)).dp))
    ){
        CustomCircleIcon(
            resource = Res.drawable.game_console,
            offsetX = (-65).dp,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        CustomCircleIcon(
            resource = Res.drawable.science,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        CustomCircleIcon(
            resource = Res.drawable.hearts,
            modifier = Modifier.align(Alignment.CenterEnd)
        )

        CustomCircleIcon(
            resource = Res.drawable.dog_bowl,
            offsetX = 65.dp,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        CustomCircleIcon(
            resource = Res.drawable.scientific,
            offsetX = 65.dp,
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        CustomCircleIcon(
            resource = Res.drawable.dumbbells,
            modifier = Modifier.align(Alignment.BottomCenter),
            offsetX = (-65).dp,
            isDumbbell = true
        )

        RedditLogo(
            size = 90.dp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun CustomCircleIcon(
    resource: DrawableResource,
    offsetX: Dp = 0.dp,
    isDumbbell: Boolean = false,
    modifier: Modifier = Modifier
){
    Icon(
        painter = painterResource(resource),
        contentDescription = "icon",
        tint = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .size(
                if (isDumbbell) 60.dp
                else 50.dp
            )
            .offset(x = offsetX)
    )
}

@Preview
@Composable
fun CircleOfIconsPrev(){
    CircleOfIcons()
}