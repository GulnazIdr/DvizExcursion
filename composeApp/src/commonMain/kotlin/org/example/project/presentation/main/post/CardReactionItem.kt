package org.example.project.presentation.main.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardReactionItem(
    content: @Composable (
        modifier: Modifier
    ) -> Unit,
){
    Box(
        modifier = Modifier.background(
            color = MaterialTheme.colorScheme.secondaryContainer,
            shape = RoundedCornerShape(40.dp)
        )
    ){
        content(Modifier.padding(10.dp))
    }
}

@Preview
@Composable
private fun CardReactionItemPrev(){
    CardReactionItem(
        content = { modifier ->
            Text(
                text = "somehting",
                modifier = modifier
            )
        }
    )
}