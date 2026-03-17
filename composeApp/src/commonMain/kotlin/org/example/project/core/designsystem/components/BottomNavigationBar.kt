package org.example.project.core.designsystem.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.aakira.napier.Napier
import org.example.project.feature.onboarding.navigation.BottomNavState
import org.jetbrains.compose.resources.painterResource

@Composable
fun BottomNavigationBar(
    startDestination: BottomDestinationItems,
    navController: BottomNavState
) {
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Box(modifier = Modifier.background(Color.Transparent)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp, bottom = 30.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Napier.wtf("some ${navController.navHostController.currentBackStackEntry}")
            BottomDestinationItems.entries.forEachIndexed { index, item ->
                BottomNavItem(
                    isSelected = selectedDestination == index,
                    onClick = {
                        navController.navigateTo(item.route)
                        selectedDestination = index
                    },
                    destination = item
                )
            }

        }
    }
}

@Composable
fun BottomNavItem(
    destination: BottomDestinationItems,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.3f else 1f,
        animationSpec = tween(durationMillis = 120)
    )
    Box(
        modifier = modifier
            .height(50.dp)
            .clickable {
                onClick()
            }
            .background(
                if (isSelected) MaterialTheme.colorScheme.onSurface
                else Color.Transparent,
                RoundedCornerShape(30.dp)
            )
            .scale(scale)
    ) {
        Box(
            modifier = Modifier.padding(12.dp)
        ) {
            Icon(
                painterResource(destination.iconResource),
                contentDescription = destination.contentDescription,
                modifier = Modifier.size(20.dp).align(Alignment.Center),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}