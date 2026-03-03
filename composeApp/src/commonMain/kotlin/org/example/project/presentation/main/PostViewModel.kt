package org.example.project.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.example.project.domain.post.PostRepository
import org.example.project.presentation.main.mappers.toPostUi
import org.example.project.presentation.main.models.PostUi

class PostViewModel(
    private val postRepository: PostRepository
): ViewModel() {
    private val _postStateFlow = MutableStateFlow<List<PostUi>>(emptyList())
    val postStateFlow: StateFlow<List<PostUi>> = _postStateFlow
        .onStart { fetchPosts() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _filteredPostStateFlow = MutableStateFlow<List<PostUi>>(emptyList())
    val filteredPostStateFLow: StateFlow<List<PostUi>> = _filteredPostStateFlow

    private fun fetchPosts(){
        viewModelScope.launch {
            _postStateFlow.value = postRepository.fetchPosts().map { it.toPostUi() }
        }
    }

    fun filterPosts(value: String){
        _filteredPostStateFlow.value = _postStateFlow.value.filter { postUi ->
            postUi.text == value
        }
    }
}