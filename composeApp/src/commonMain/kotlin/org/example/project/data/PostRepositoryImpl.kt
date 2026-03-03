package org.example.project.data

import org.example.project.domain.post.Post
import org.example.project.domain.post.PostRepository

class PostRepositoryImpl: PostRepository {
    override suspend fun fetchPosts(): List<Post> {
        return listOf(
            Post(
                id = 1,
                text = "something",
                image = "https://www.malwarebytes.com/wp-content/uploads/sites/2/2025/03/reddit.jpg",
                commentAmount = 3,
                favoriteAmount = 2
            ),
            Post(
                id = 2,
                text = "post",
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsxmzEJF2eN068DCn78Wk7JTi-GWOm0S61eg&s",
                commentAmount = 8,
                favoriteAmount = 20
            ),
            Post(
                id = 3,
                text = "information",
                image = "https://support.dlvrit.com/hc/article_attachments/4414593730843/mceclip0.png",
                commentAmount = 23,
                favoriteAmount = 21
            ),
            Post(
                id = 4,
                text = "something post without image",
                commentAmount = 4,
                favoriteAmount = 20
            )
        )
    }
}