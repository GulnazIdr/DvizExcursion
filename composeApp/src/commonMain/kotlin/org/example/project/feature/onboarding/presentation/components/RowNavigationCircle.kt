package org.example.project.feature.onboarding.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.feature.onboarding.navigation.BoardingDestination
import org.example.project.core.navigation.CustomNavState

@Composable
fun RowNavigationCircle(
    startDestination: BoardingDestination,
    navController: CustomNavState,
    setBoardingViewed: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        BoardingDestination.entries.forEachIndexed { index, destination ->
            NavigationCircle(
                isActive = selectedDestination == index,
                onClick = {
                    if (destination == BoardingDestination.BOARDING2) {
                        setBoardingViewed()
                    }
                    navController.navigateToLaunchSingle(destination.route)
                    selectedDestination = index
                }
            )

            if (index == 0){
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }
}