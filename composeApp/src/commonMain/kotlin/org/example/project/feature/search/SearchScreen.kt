package org.example.project.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.core.designsystem.components.CircleLoading
import org.example.project.feature.course_catalog.components.CourseList
import org.example.project.feature.search.components.SearchTopAppBar
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    navigateToMain: () -> Unit,
    navigateToCourseDetail: (id: Int) -> Unit,
    courseViewModel: SearchViewModel = koinViewModel<SearchViewModel>(),
    modifier: Modifier = Modifier
) {
    val searchedCourseState by courseViewModel.searchedCourseState.collectAsStateWithLifecycle()
    val lastSearched by courseViewModel.searchValue.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        PullToRefreshBox(
            isRefreshing = searchedCourseState.isRefreshing,
            onRefresh = { courseViewModel.refresh(lastSearched) }
        ) {
            SearchTopAppBar(
                onBack = {
                    navigateToMain()
                },
                onValueChanged = { courseViewModel.onSearch(it) },
                input = lastSearched,
                modifier = modifier
            )

            if (searchedCourseState.isLoading) {
                CircleLoading()
            } else {
                Spacer(modifier = Modifier.height(20.dp))

                CourseList(
                    courseList = searchedCourseState.courseList,
                    isPageEnded = false,
                    loadMore = {},
                    isSearchScreen = true,
                    onCourse = { navigateToCourseDetail(it) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun SearchScreenPrev(
) {
    SearchScreen(
        navigateToMain = {},
        navigateToCourseDetail = {}
    )
}