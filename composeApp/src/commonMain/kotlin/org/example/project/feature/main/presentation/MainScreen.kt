package org.example.project.feature.main.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.feature.main.presentation.components.MainTopAppBar
import org.example.project.feature.main.presentation.components.SearchBar
import org.example.project.feature.main.presentation.post.PostCardItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(){
    val postViewModel = koinViewModel<PostViewModel>()
    val posts by postViewModel.postState.collectAsStateWithLifecycle()

    Scaffold{ paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                MainTopAppBar(
                    onMenu = {},
                    onAddPost = {},
                    onProfile = {}
                )

                SearchBar(
                    onValueChanged = { postViewModel.filterPosts(it) }
                )

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn {
                    items(
                        items = posts,
                        key = { post ->
                            post.id
                        }
                    ) { post ->
                        PostCardItem(
                            postUi = post,
                            isFirst = post.id == 1
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPrev(){
    MainScreen()
}