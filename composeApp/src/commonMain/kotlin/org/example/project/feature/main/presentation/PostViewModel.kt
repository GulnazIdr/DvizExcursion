package org.example.project.feature.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.example.project.feature.main.domain.PostRepository
import org.example.project.feature.main.presentation.mappers.toPostUi
import org.example.project.feature.main.presentation.models.PostUi

class PostViewModel(
    private val postRepository: PostRepository
): ViewModel() {
    private val _postState = MutableStateFlow<List<PostUi>>(emptyList())
    val postState: StateFlow<List<PostUi>> = _postState
        .onStart { fetchPosts() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _filteredPostState = MutableStateFlow<List<PostUi>>(emptyList())
    val filteredPostStateF: StateFlow<List<PostUi>> = _filteredPostState

    private fun fetchPosts(){
        viewModelScope.launch {
            _postState.value = postRepository.fetchPosts().map { it.toPostUi() }
        }
    }

    fun filterPosts(value: String){
        _filteredPostState.value = _postState.value.filter { postUi ->
            postUi.text == value
        }
    }
}