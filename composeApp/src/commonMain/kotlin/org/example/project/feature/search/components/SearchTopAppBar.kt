package org.example.project.feature.search.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.project.core.designsystem.components.BasicTopAppBar
import org.example.project.feature.main.presentation.components.SearchBar

@Composable
fun SearchTopAppBar(
    onBack: () -> Unit,
    onValueChanged: (String) -> Unit,
    input: String,
    modifier: Modifier = Modifier
) {
    BasicTopAppBar(
        onBack = onBack
    ){
        Spacer(modifier = Modifier.width(10.dp))

        SearchBar(
            onValueChanged = onValueChanged,
            input = input
        )
    }
}

@Preview
@Composable
private fun SearchTopAppBarPrev() {
    SearchTopAppBar(
        onBack = {},
        onValueChanged = {},
        input = ""
    )
}