package org.example.project.feature.main.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.core.designsystem.components.ErrorDialog
import org.example.project.feature.main.presentation.components.CourseList
import org.example.project.feature.main.presentation.components.MainTopAppBar
import org.example.project.feature.main.presentation.components.SearchBar
import org.example.project.core.designsystem.components.CircleLoading
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(
    navigateToSearch: () -> Unit,
    navigateToCourseDetail: (id: Int) -> Unit,
    courseViewModel: CourseViewModel = koinViewModel<CourseViewModel>()
){
    val courseFetchResult by courseViewModel.courseFetchedResult.collectAsStateWithLifecycle()
    val isPageEnded by courseViewModel.isPageEnded
    val isDataLoading by courseViewModel.isDataLoading

    Scaffold{ paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                MainTopAppBar(
                    onMenu = {}
                )

                courseFetchResult.Display(
                    onSuccess = { courseList ->
                        SearchBar(
                            onValueChanged = {},
                            onClick = navigateToSearch
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        CourseList(
                            courseList = courseList,
                            isPageEnded = isPageEnded,
                            loadMore = { courseViewModel.fetchCourses() },
                            isDataLoading = isDataLoading,
                            onCourse = {
                                navigateToCourseDetail(it)
                            }
                        )
                    },
                    onLoading = {
                        CircleLoading()
                    },
                    onError = { error ->
                        ErrorDialog(
                            errorMessage = error,
                            onRetry = { courseViewModel.fetchCourses() }
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPrev(){
    MainScreen(
        navigateToSearch = {},
        navigateToCourseDetail = {}
    )
}