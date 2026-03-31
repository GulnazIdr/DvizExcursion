package org.gulnazidr.stepik.feature.course_detail.presentation.components

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
import org.gulnazidr.stepik.core.designsystem.components.NavigationButton
import org.gulnazidr.stepik.core.designsystem.components.PriceText
import org.gulnazidr.stepik.core.designsystem.ui_logic.model.CourseDetailUi
import org.jetbrains.compose.resources.stringResource
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.clock
import stepik.composeapp.generated.resources.course_detail_lessons_text
import stepik.composeapp.generated.resources.course_detail_start_lesson_text
import stepik.composeapp.generated.resources.level

@Composable
fun CourseDetailContent(
    courseDetailUi: CourseDetailUi,
    scrollState: ScrollState,
    onStartLessonClick: () -> Unit,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
) {
    val textColor = MaterialTheme.colorScheme.onSecondaryContainer

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
                        color = textColor
                    ),
                    modifier = Modifier.weight(1f)
                )
                PriceText(
                    courseDetailUi.courseUi.price,
                    priceColor = textColor
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (courseDetailUi.workloadTime.isNotEmpty()) {
                    DescriptionItem(
                        resource = Res.drawable.clock,
                        text = courseDetailUi.workloadTime,
                        textColor = textColor,
                        modifier = Modifier.weight(2f)
                    )
                }

                if (courseDetailUi.difficultyLevel.isNotEmpty()) {
                    DescriptionItem(
                        resource = Res.drawable.level,
                        text = courseDetailUi.difficultyLevel.capitalize(
                            Locale.current
                        ),
                        textColor = textColor,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            DescriptionComponent(
                description = courseDetailUi.courseUi.description,
                textColor = textColor
            )

            AudienceComponent(
                textColor = textColor,
                targetAudience = courseDetailUi.targetAudience
            )

            RequirementComponent(
                requirements = courseDetailUi.requirements,
                textColor = textColor
            )

            AquiredSkillsComponent(
                textColor = textColor,
                acquiredSkills = courseDetailUi.acquiredSkills
            )

            AquiredAssetsComponent(
                acquiredAssets = courseDetailUi.acquiredAssets,
                textColor = textColor
            )

            LearningFormatComponent(
                learningFormat = courseDetailUi.learningFormat,
                textColor = textColor,
            )

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
                        color = textColor
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