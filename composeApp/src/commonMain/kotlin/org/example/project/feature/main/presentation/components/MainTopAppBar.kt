package org.example.project.feature.main.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.add
import dvizexcursion.composeapp.generated.resources.menu
import dvizexcursion.composeapp.generated.resources.reddit_logo_text
import dvizexcursion.composeapp.generated.resources.reddit_profile
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainTopAppBar(
    onMenu: () -> Unit,
    onAddPost: () -> Unit,
    onProfile: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(Res.drawable.menu),
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                contentDescription = "menu icon",
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = onMenu)
            )

            Spacer(modifier = Modifier.width(15.dp))

            Icon(
                painter = painterResource(Res.drawable.reddit_logo_text),
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                contentDescription = "reddit logo icon",
                modifier = Modifier.size(60.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(Res.drawable.add),
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                contentDescription = "add post icon",
                modifier = Modifier
                    .size(20.dp)
                    .clickable(onClick = onAddPost)
            )

            Spacer(modifier = Modifier.width(15.dp))

            Icon(
                painter = painterResource(Res.drawable.reddit_profile),
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                contentDescription = "profile default icon",
                modifier = Modifier
                    .size(30.dp)
                    .clickable(onClick = onProfile)
            )
        }
    }
}

@Preview
@Composable
fun MainTopAppBarPrev(){
    MainTopAppBar(
        onMenu = {},
        onProfile = {},
        onAddPost = {}
    )
}