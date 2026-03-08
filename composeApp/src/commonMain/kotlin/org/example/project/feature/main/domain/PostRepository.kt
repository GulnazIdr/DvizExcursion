package org.example.project.feature.main.domain

interface PostRepository {
    suspend fun fetchPosts(): List<Post>
}