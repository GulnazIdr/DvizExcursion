package org.example.project.feature.course_detail.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import org.example.project.core.designsystem.components.NavigationButton
import org.example.project.core.designsystem.components.PriceText
import org.example.project.feature.course_catalog.presentation.models.CourseDetailUi
import org.jetbrains.compose.resources.stringResource
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.checkmark
import stepik.composeapp.generated.resources.clock
import stepik.composeapp.generated.resources.course_detail_description_text
import stepik.composeapp.generated.resources.course_detail_learning_format_text
import stepik.composeapp.generated.resources.course_detail_lessons_text
import stepik.composeapp.generated.resources.course_detail_requirements_text
import stepik.composeapp.generated.resources.course_detail_skill_acquire_text
import stepik.composeapp.generated.resources.course_detail_start_lesson_text
import stepik.composeapp.generated.resources.course_detail_target_audience_text
import stepik.composeapp.generated.resources.course_detail_what_get_text
import stepik.composeapp.generated.resources.level
import stepik.composeapp.generated.resources.requirement
import stepik.composeapp.generated.resources.skill

@Composable
fun CourseDetailContent(
    courseDetailUi: CourseDetailUi,
    scrollState: ScrollState,
    onStartLessonClick: () -> Unit,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
){
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh
    ) {
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
                    DescriptionItem(
                        resource = Res.drawable.clock,
                        text = courseDetailUi.workloadTime
                    )

                    Spacer(modifier = Modifier.width(24.dp))
                }

                if (courseDetailUi.difficultyLevel.isNotEmpty())
                    DescriptionItem(
                        resource = Res.drawable.level,
                        text = courseDetailUi.difficultyLevel.capitalize(
                            Locale.current
                        )
                    )
            }

            if (courseDetailUi.courseUi.description.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))


                DescriptionBlock(
                    title = Res.string.course_detail_description_text
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
                    title = Res.string.course_detail_target_audience_text
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
                    title = Res.string.course_detail_requirements_text
                ) {
                    Spacer(modifier = Modifier.height(6.dp))
                    DescriptionItem(
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
                    title = Res.string.course_detail_skill_acquire_text
                ) {
                    Spacer(modifier = Modifier.height(6.dp))
                    courseDetailUi.acquiredSkills.forEach { skill ->
                        Row(verticalAlignment = Alignment.Top) {
                            DescriptionItem(
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

            if (courseDetailUi.acquiredAssets.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))

                DescriptionBlock(
                    title = Res.string.course_detail_what_get_text
                ) {
                    Spacer(modifier = Modifier.height(6.dp))
                    courseDetailUi.acquiredAssets.forEach { asset ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            DescriptionItem(
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
                    title = Res.string.course_detail_learning_format_text
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
                    text = stringResource(Res.string.course_detail_lessons_text) +
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
                    text = stringResource(Res.string.course_detail_start_lesson_text)
                )
            }
        }
    }
}