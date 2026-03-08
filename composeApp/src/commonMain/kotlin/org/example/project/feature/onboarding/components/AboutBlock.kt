package org.example.project.feature.onboarding.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.comment_descr_about
import dvizexcursion.composeapp.generated.resources.comment_title_about
import dvizexcursion.composeapp.generated.resources.post_descr_about
import dvizexcursion.composeapp.generated.resources.post_title_about
import dvizexcursion.composeapp.generated.resources.vote_descr_about
import dvizexcursion.composeapp.generated.resources.vote_title_about
import org.jetbrains.compose.resources.stringResource

@Composable
fun AboutBlock(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        AboutCard(
            title = stringResource(Res.string.post_title_about),
            descr = stringResource(Res.string.post_descr_about),
            color = MaterialTheme.colorScheme.tertiaryContainer,
        )

        Spacer(modifier = Modifier.height(20.dp))

        AboutCard(
            title = stringResource(Res.string.comment_title_about),
            descr = stringResource(Res.string.comment_descr_about),
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.align(Alignment.End)
        )

        Spacer(modifier = Modifier.height(20.dp))

        AboutCard(
            title = stringResource(Res.string.vote_title_about),
            descr = stringResource(Res.string.vote_descr_about),
            color = MaterialTheme.colorScheme.surfaceContainer
        )
    }
}

@Preview
@Composable
fun AboutBlockPrev(){
    AboutBlock(
    )
}