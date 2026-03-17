package org.example.project.feature.onboarding.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RowNavigationCircle(
    activeIndex: Int,
    navigateToBoarding1: () -> Unit,
    navigateToBoarding2: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        NavigationCircle(
            isActive = activeIndex == 1,
            onClick = navigateToBoarding1
        )

        Spacer(modifier = Modifier.width(20.dp))

        NavigationCircle(
            isActive = activeIndex == 2,
            onClick = navigateToBoarding2
        )

        Spacer(modifier = Modifier.width(20.dp))
    }
}

@Preview
@Composable
fun RowNavigationCirclePrev(){
    RowNavigationCircle(
        activeIndex = 1,
        navigateToBoarding1 = {},
        navigateToBoarding2 = {}
    )
}