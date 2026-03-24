package org.example.project.feature.course_catalog.presentation.components

import androidx.compose.foundation.clickable
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
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.menu
import stepik.composeapp.generated.resources.stepik_logo_text
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainTopAppBar(
    onMenu: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = Modifier.fillMaxWidth(),
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
            painter = painterResource(Res.drawable.stepik_logo_text),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = "stepik logo icon",
            modifier = Modifier.size(95.dp)
        )
    }
}

@Preview
@Composable
fun MainTopAppBarPrev(){
    MainTopAppBar(
        onMenu = {}
    )
}