package org.example.project.feature.main.presentation.course

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.free_text
import org.example.project.core.designsystem.components.getWindowHeight
import org.example.project.feature.main.presentation.models.CourseUi
import org.example.project.presentation.components.CircleLoading
import org.jetbrains.compose.resources.stringResource

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
                .height((getWindowHeight() * 0.14 ).dp)
                .background(MaterialTheme.colorScheme.background)
                .clickable(onClick = {onCourse(courseUi.id)})
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(10.dp),
            ) {
                Column(
                    modifier.weight(1f),
                    verticalArrangement = Arrangement.SpaceBetween
                ){
                    CourseImage(
                        image = courseUi.image,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text =
                            if (courseUi.price == 0) stringResource(Res.string.free_text)
                            else "${courseUi.price}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
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
                            text = "123",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "4.7",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text =
                            if (courseUi.description.length > 13)
                                courseUi.description.take(13) + "..."
                            else courseUi.description,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    )
                }
            }
        }

        if (!isLast) {
            Spacer(
                modifier = Modifier.height(5.dp)
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.secondaryContainer
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )
        }
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