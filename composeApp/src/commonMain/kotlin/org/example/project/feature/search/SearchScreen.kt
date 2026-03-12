package org.example.project.feature.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.feature.main.presentation.components.CourseList
import org.example.project.feature.search.components.SearchTopAppBar
import org.example.project.core.designsystem.components.CircleLoading
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    navigateToMain: () -> Unit,
    navigateToCourseDetail: (id: Int) -> Unit,
    courseViewModel: SearchViewModel = koinViewModel<SearchViewModel>()
){
    val searchedCourse by courseViewModel.searchedCourseState.collectAsStateWithLifecycle()
    val isSearching by courseViewModel.isSearching
    val lastSearched by courseViewModel.searchValue.collectAsStateWithLifecycle()

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                SearchTopAppBar(
                    onBack = {
                        navigateToMain()
                    },
                    onValueChanged = { courseViewModel.onSearch(
                        it
                    ) },
                    input = lastSearched,
                    modifier = Modifier.padding(paddingValues)
                )

                if (isSearching)
                    CircleLoading()
                else {
                    Spacer(modifier = Modifier.height(20.dp))

                    CourseList(
                        courseList = searchedCourse,
                        isPageEnded = false,
                        loadMore = {},
                        isSearchScreen = true,
                        onCourse = {
                            navigateToCourseDetail(it)
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SearchScreenPrev(
) {
    SearchScreen(
        navigateToMain = {},
        navigateToCourseDetail = {}
    )
}