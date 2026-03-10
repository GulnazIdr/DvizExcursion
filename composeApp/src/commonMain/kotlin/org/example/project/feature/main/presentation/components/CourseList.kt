package org.example.project.feature.main.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.aakira.napier.Napier
import org.example.project.feature.main.presentation.course.CourseCardItem
import org.example.project.feature.main.presentation.models.CourseUi
import org.example.project.presentation.components.CircleLoading
import org.koin.core.qualifier.qualifier

@Composable
fun CourseList(
    courseList: List<CourseUi>,
    isPageEnded: Boolean,
    loadMore: () -> Unit,
    isDataLoading: Boolean = false,
    isSearchScreen: Boolean = false,
    onCourse: (Int) -> Unit
){
    var scrollFinished by remember { mutableStateOf(false) }

    Column{
        LazyColumn {
            items(
                items = courseList.distinctBy { it.id },
                key = { post ->
                    post.id
                }
            ) { post ->
                scrollFinished = post.id == courseList.last().id && !isDataLoading

                CourseCardItem(
                    courseUi = post,
                    isLast = scrollFinished,
                    onCourse = onCourse
                )
            }

            if (isDataLoading)
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    CircleLoading()
                }
        }

        if ((scrollFinished && !isPageEnded) && !isSearchScreen) {
            Napier.wtf("${courseList.size}")
            loadMore()
        }
    }
}