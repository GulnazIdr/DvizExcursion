package org.example.project.feature.main.presentation.course

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
import androidx.compose.ui.unit.dp
import org.example.project.core.designsystem.components.PriceText
import org.example.project.feature.main.presentation.models.CourseUi

@Composable
fun CourseCardItem(
    courseUi: CourseUi,
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
                .clickable(onClick = { onCourse(courseUi.id) })
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(10.dp),
            ) {
                Column(
                    modifier.weight(1f),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    CourseImage(
                        image = courseUi.image,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    PriceText(
                        price = courseUi.price,
                        priceColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    modifier.weight(2f)
                ) {
                    Text(
                        text = courseUi.title,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row {
                        Text(
                            text = "${courseUi.learnersCount}\uD83D\uDC64",
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
                            if (courseUi.description.length > 50)
                                courseUi.description.take(51) + "..."
                            else courseUi.description,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    )
                }
            }
        }

        if (!isLast)
            Spacer(modifier = Modifier.height(10.dp))
    }
}

//@Preview
//@Composable
//private fun CourseCardItemPrev(){
//    CourseCardItem(
//        courseUi = CourseUi(
//            1,
//            "Post text information",
//            description = "dsds",
//            image = "https://www.reddit.com/r/beards/comments/1rfs9kv/which_beard_look_works_better/#lightbox",
//            commentAmount = 2,
//            favoriteAmount = 20,
//            price = 100
//        ),
//        isLast = true
//    )
//}