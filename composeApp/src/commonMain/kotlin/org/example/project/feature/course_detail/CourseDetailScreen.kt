package org.example.project.feature.course_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.checkmark
import dvizexcursion.composeapp.generated.resources.clock
import dvizexcursion.composeapp.generated.resources.description_text
import dvizexcursion.composeapp.generated.resources.learning_format_text
import dvizexcursion.composeapp.generated.resources.lessons_text
import dvizexcursion.composeapp.generated.resources.level
import dvizexcursion.composeapp.generated.resources.requirement
import dvizexcursion.composeapp.generated.resources.requirements_text
import dvizexcursion.composeapp.generated.resources.skill
import dvizexcursion.composeapp.generated.resources.skill_acquire_text
import dvizexcursion.composeapp.generated.resources.start_lesson_text
import dvizexcursion.composeapp.generated.resources.target_audience_text
import dvizexcursion.composeapp.generated.resources.what_get_text
import org.example.project.core.designsystem.components.BasicTopAppBar
import org.example.project.core.designsystem.components.ErrorDialog
import org.example.project.core.designsystem.components.NavigationButton
import org.example.project.core.designsystem.components.PriceText
import org.example.project.feature.course_detail.components.DescEnumItem
import org.example.project.feature.course_detail.components.DescriptionBlock
import org.example.project.feature.main.presentation.CourseViewModel
import org.example.project.presentation.components.CircleLoading
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CourseDetailsCard(
    onStartLessonClick: () -> Unit,
    navigateToMain: () -> Unit,
    courseViewModel: CourseViewModel = koinViewModel<CourseViewModel>()
) {
    val courseDetailFetchRes by courseViewModel.fetchedCourseResult.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    BackHandler(
        onBack = navigateToMain
    )

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                BasicTopAppBar(
                    onBack = navigateToMain
                )

                Spacer(modifier = Modifier.height(10.dp))

                Card(
                    modifier = Modifier.fillMaxSize(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    courseDetailFetchRes.Display(
                        onSuccess = { courseDetailUi ->
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                                    .verticalScroll(scrollState),
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = courseDetailUi.courseUi.title,
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            color = MaterialTheme.colorScheme.background
                                        ),
                                        modifier = Modifier.weight(1f)
                                    )
                                    PriceText(
                                        courseDetailUi.courseUi.price,
                                        priceColor = MaterialTheme.colorScheme.background
                                    )
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    if (courseDetailUi.workloadTime.isNotEmpty()) {
                                        DescEnumItem(
                                            resource = Res.drawable.clock,
                                            text = courseDetailUi.workloadTime
                                        )

                                        Spacer(modifier = Modifier.width(24.dp))
                                    }

                                    if (courseDetailUi.difficultyLevel.isNotEmpty())
                                        DescEnumItem(
                                            resource = Res.drawable.level,
                                            text = courseDetailUi.difficultyLevel.capitalize(
                                                Locale.current
                                            )
                                        )
                                }

                                if (courseDetailUi.courseUi.description.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(16.dp))


                                    DescriptionBlock(
                                        title = Res.string.description_text
                                    ) {
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(
                                            text = courseDetailUi.courseUi.description,
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                color = MaterialTheme.colorScheme.background
                                            ),
                                        )
                                    }
                                }

                                if (courseDetailUi.targetAudience.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(16.dp))

                                    DescriptionBlock(
                                        title = Res.string.target_audience_text
                                    ) {
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(
                                            text = courseDetailUi.targetAudience.replace(
                                                "\\n", "\n"
                                            ),
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                color = MaterialTheme.colorScheme.background
                                            ),
                                        )
                                    }
                                }

                                if (courseDetailUi.requirements.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(16.dp))

                                    DescriptionBlock(
                                        title = Res.string.requirements_text
                                    ) {
                                        Spacer(modifier = Modifier.height(6.dp))
                                        DescEnumItem(
                                            resource = Res.drawable.requirement,
                                            text = courseDetailUi.requirements
                                                .replace(Regex("<.*?>"), "")
                                                .replace(Regex("-"), "")
                                        )
                                    }
                                }

                                if (courseDetailUi.acquiredSkills.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(16.dp))

                                    DescriptionBlock(
                                        title = Res.string.skill_acquire_text
                                    ) {
                                        Spacer(modifier = Modifier.height(6.dp))
                                        courseDetailUi.acquiredSkills.forEach { skill ->
                                            Row(verticalAlignment = Alignment.Top) {
                                                DescEnumItem(
                                                    resource = Res.drawable.skill,
                                                    text = skill.replace(
                                                        Regex("—"), ""
                                                    )
                                                )
                                            }
                                            Spacer(modifier = Modifier.height(4.dp))
                                        }
                                    }
                                }

                                if ( courseDetailUi.acquiredAssets.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(16.dp))

                                    DescriptionBlock(
                                        title = Res.string.what_get_text
                                    ) {
                                        Spacer(modifier = Modifier.height(6.dp))
                                        courseDetailUi.acquiredAssets.forEach { asset ->
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                DescEnumItem(
                                                    resource = Res.drawable.checkmark,
                                                    text = asset
                                                )
                                            }
                                            Spacer(modifier = Modifier.height(4.dp))
                                        }
                                    }
                                }

                                if (courseDetailUi.learningFormat.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(16.dp))

                                    DescriptionBlock(
                                        title = Res.string.learning_format_text
                                    ) {
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(
                                            text = courseDetailUi.learningFormat.replace(
                                                Regex("<.*?>"),
                                                ""
                                            ),
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                color = MaterialTheme.colorScheme.background
                                            ),
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(24.dp))

                                Spacer(modifier = Modifier.weight(1f))

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth(),
                                ) {
                                    Text(
                                        text = stringResource(Res.string.lessons_text) +
                                                "${courseDetailUi.lessonsCount}",
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            color = MaterialTheme.colorScheme.background
                                        ),
                                    )

                                    Spacer(
                                        modifier = Modifier.width(10.dp)
                                    )

                                    NavigationButton(
                                        onBtnClick = onStartLessonClick,
                                        text = stringResource(Res.string.start_lesson_text)
                                    )
                                }
                            }
                        },
                        onError = { error ->
                            ErrorDialog(
                                errorMessage = error,
                                onRetry = {}
                            )
                        },
                        onLoading = {
                            CircleLoading()
                        }
                    )
                }
            }
        }
    }
}