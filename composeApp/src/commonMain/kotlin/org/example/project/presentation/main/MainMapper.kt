package org.example.project.presentation.main

import org.example.project.domain.post.Post
import org.example.project.presentation.main.models.PostUi

fun Post.toPostUi(): PostUi{
    return PostUi(
        id = id,
        text = text,
        image = image,
        commentAmount = commentAmount,
        favoriteAmount = favoriteAmount
    )
}