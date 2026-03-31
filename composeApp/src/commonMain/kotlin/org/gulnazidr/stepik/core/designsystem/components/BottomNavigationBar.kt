package org.gulnazidr.stepik.core.designsystem.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.gulnazidr.stepik.core.navigation.CustomNavState
import org.jetbrains.compose.resources.painterResource

@Composable
fun BottomNavigationBar(
    startDestination: BottomDestinationItems,
    navController: CustomNavState
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
            BottomDestinationItems.entries.forEachIndexed { index, item ->
                BottomNavItem(
                    isSelected = selectedDestination == index,
                    onClick = {
                        navController.navigateToPopUpTo(
                            BottomDestinationItems.HOME.route,
                            item.route
                        )
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

    Box(
        modifier = modifier
            .height(50.dp)
            .clickable {
                onClick()
            }
            .background(
                if (isSelected) MaterialTheme.colorScheme.onSurface
                else Color.Transparent,
                RoundedCornerShape(20.dp)
            )
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