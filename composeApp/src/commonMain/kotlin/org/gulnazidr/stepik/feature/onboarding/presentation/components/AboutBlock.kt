package org.gulnazidr.stepik.feature.onboarding.presentation.components

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
import org.jetbrains.compose.resources.stringResource
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.on_boarding2_new_prof_descr_about
import stepik.composeapp.generated.resources.on_boarding2_new_prof_title_about
import stepik.composeapp.generated.resources.on_boarding2_skills_descr_about
import stepik.composeapp.generated.resources.on_boarding2_skills_title_about
import stepik.composeapp.generated.resources.on_boarding2_stepik_about
import stepik.composeapp.generated.resources.on_boarding2_stepik_descr_about
import stepik.composeapp.generated.resources.on_boarding2_study_descr_about
import stepik.composeapp.generated.resources.on_boarding2_study_title_about

@Composable
fun AboutBlock(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        AboutCard(
            title = stringResource(Res.string.on_boarding2_stepik_about),
            descr = stringResource(Res.string.on_boarding2_stepik_descr_about),
            color = MaterialTheme.colorScheme.tertiaryContainer,
        )

        Spacer(modifier = Modifier.height(20.dp))

        AboutCard(
            title = stringResource(Res.string.on_boarding2_new_prof_title_about),
            descr = stringResource(Res.string.on_boarding2_new_prof_descr_about),
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.align(Alignment.End)
        )

        Spacer(modifier = Modifier.height(20.dp))

        AboutCard(
            title = stringResource(Res.string.on_boarding2_skills_title_about),
            descr = stringResource(Res.string.on_boarding2_skills_descr_about),
            color = MaterialTheme.colorScheme.surfaceContainer
        )

        Spacer(modifier = Modifier.height(20.dp))

        AboutCard(
            title = stringResource(Res.string.on_boarding2_study_title_about),
            descr = stringResource(Res.string.on_boarding2_study_descr_about),
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