package org.example.project.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.project.presentation.main.components.MainTopAppBar
import org.example.project.presentation.main.components.SearchBar
import org.example.project.presentation.main.post.PostCardItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(){
    val postViewModel = koinViewModel<PostViewModel>()
    val posts = postViewModel.postStateFlow.collectAsState().value

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