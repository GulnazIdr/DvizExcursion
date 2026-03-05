package org.example.project.presentation.main.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.redidit_circle
import dvizexcursion.composeapp.generated.resources.search_hint
import org.example.project.presentation.components.CustomTextField
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchBar(
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var input by remember { mutableStateOf("") }
    var isHintVisible by remember { mutableStateOf(true) }

    Box(
        modifier = modifier
            .border(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.tertiary
                    )
                ),
                width = 2.dp,
                shape = RoundedCornerShape(30.dp)
            )
            .fillMaxWidth()
            .height(50.dp)
            .clickable(onClick = {isHintVisible = false})
    ){
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isHintVisible)
                Row(
                    modifier = Modifier.fillMaxWidth() ,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(17.dp))
                    Icon(
                        painter = org.jetbrains.compose.resources.painterResource(
                            Res.drawable.redidit_circle
                        ),
                        contentDescription = "search icon",
                        modifier = Modifier.size(40.dp),
                        tint = Color.Unspecified
                    )

                    Spacer(modifier = Modifier.width(7.dp))

                    Text(
                        text = stringResource(Res.string.search_hint),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    )
                }

            CustomTextField(
                value = input,
                onValueChange = {
                    isHintVisible = it.isEmpty()
                    input = it
                    onValueChanged(it)
                }
            )
        }
    }
}


@Preview
@Composable
fun SearchBarPrev(){
    SearchBar(
        onValueChanged = {}
    )
}

