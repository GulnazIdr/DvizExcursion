package org.gulnazidr.stepik.feature.course_catalog.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.gulnazidr.stepik.core.designsystem.components.PriceText
import org.gulnazidr.stepik.core.designsystem.ui_logic.model.CourseDetailUi
import org.gulnazidr.stepik.core.designsystem.ui_logic.model.CourseUi

@Composable
fun CourseCardItem(
    courseDetailUi: CourseDetailUi,
    isLast: Boolean,
    onCourse: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.outlineVariant,
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable(onClick = { onCourse(courseDetailUi.courseUi.id) })
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(10.dp),
            ) {
                Column(
                    modifier.weight(1f),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    CourseImage(
                        image = courseDetailUi.courseUi.image,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    PriceText(
                        price = courseDetailUi.courseUi.price,
                        priceColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    modifier.weight(2f)
                ) {
                    Text(
                        text = courseDetailUi.courseUi.title,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row {
                        Text(
                            text = "${courseDetailUi.courseUi.learnersCount}\uD83D\uDC64",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "4.7☆",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text =
                            if (courseDetailUi.courseUi.description.length > 50)
                                courseDetailUi.courseUi.description.take(51) + "..."
                            else courseDetailUi.courseUi.description,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    )
                }
            }
        }

        if (!isLast) {
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview
@Composable
private fun CourseCardItemPrev(){
    CourseCardItem(
        courseDetailUi = CourseDetailUi(
            courseUi = CourseUi(
                id = 1,
                title = "title",
                description = "description",
                image = "",
                price = 12.0,
                commentAmount = 1,
                favoriteAmount = 3,
                learnersCount = 2,
                authorList = emptyList()
            ),
        ),
        isLast = false,
        onCourse = {}
    )
}