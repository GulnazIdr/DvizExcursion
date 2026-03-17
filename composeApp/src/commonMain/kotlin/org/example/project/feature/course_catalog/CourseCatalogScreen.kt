package org.example.project.feature.course_catalog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.core.designsystem.components.CircleLoading
import org.example.project.core.designsystem.components.ErrorDialog
import org.example.project.feature.course_catalog.components.CourseList
import org.example.project.feature.course_catalog.presentation.components.MainTopAppBar
import org.example.project.feature.course_catalog.components.SearchBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CourseCatalogScreen(
    navigateToSearch: () -> Unit,
    navigateToCourseDetail: (id: Int) -> Unit,
    courseViewModel: CourseViewModel = koinViewModel<CourseViewModel>(),
    modifier: Modifier = Modifier
) {
    var isDismissed by rememberSaveable { mutableStateOf(false) }
    val courseFetchState by courseViewModel.courseFetchedState.collectAsStateWithLifecycle()

    PullToRefreshBox(
        isRefreshing = courseFetchState.isRefreshing,
        onRefresh = { courseViewModel.refresh() }
    ) {
        Column{
            MainTopAppBar(
                onMenu = {}
            )
            courseFetchState.courseFetchedResult.Display(
                onSuccess = { courseList ->
                    SearchBar(
                        onValueChanged = {},
                        onClick = navigateToSearch
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    CourseList(
                        courseList = courseList,
                        isPageEnded = courseFetchState.isPageEnded,
                        loadMore = { courseViewModel.fetchCourses() },
                        isDataLoading = courseFetchState.isDataLoading,
                        onCourse = { navigateToCourseDetail(it) }
                    )

                },
                onLoading = {
                    CircleLoading()
                },
                onError = { error ->
                    ErrorDialog(
                        errorMessage = error,
                        onRetry = { courseViewModel.fetchCourses() },
                        isVisible = !isDismissed,
                        onClose = { isDismissed = true }
                    )
                },
                isDismissed = isDismissed
            )
        }
    }
}

@Preview
@Composable
private fun CourseCatalogPrev(){
    CourseCatalogScreen(
        navigateToSearch = {},
        navigateToCourseDetail = {}
    )
}