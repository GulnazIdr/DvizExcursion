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
import dvizexcursion.composeapp.generated.resources.new_profession_descr_about
import dvizexcursion.composeapp.generated.resources.new_profession_title_about
import dvizexcursion.composeapp.generated.resources.skills_descr_about
import dvizexcursion.composeapp.generated.resources.skills_title_about
import dvizexcursion.composeapp.generated.resources.stepik_about
import dvizexcursion.composeapp.generated.resources.stepik_descr_about
import dvizexcursion.composeapp.generated.resources.study_descr_about
import dvizexcursion.composeapp.generated.resources.study_title_about
import org.jetbrains.compose.resources.stringResource

@Composable
fun AboutBlock(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        AboutCard(
            title = stringResource(Res.string.stepik_about),
            descr = stringResource(Res.string.stepik_descr_about),
            color = MaterialTheme.colorScheme.tertiaryContainer,
        )

        Spacer(modifier = Modifier.height(20.dp))

        AboutCard(
            title = stringResource(Res.string.new_profession_title_about),
            descr = stringResource(Res.string.new_profession_descr_about),
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.align(Alignment.End)
        )

        Spacer(modifier = Modifier.height(20.dp))

        AboutCard(
            title = stringResource(Res.string.skills_title_about),
            descr = stringResource(Res.string.skills_descr_about),
            color = MaterialTheme.colorScheme.surfaceContainer
        )

        Spacer(modifier = Modifier.height(20.dp))

        AboutCard(
            title = stringResource(Res.string.study_title_about),
            descr = stringResource(Res.string.study_descr_about),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Preview
@Composable
fun AboutBlockPrev(){
    AboutBlock(
    )
}