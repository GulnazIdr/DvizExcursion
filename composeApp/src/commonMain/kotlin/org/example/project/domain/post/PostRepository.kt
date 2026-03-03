package org.example.project.domain.post

interface PostRepository {
    suspend fun fetchPosts(): List<Post>
}