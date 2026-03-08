package org.example.project.feature.main.presentation.mappers

import org.example.project.feature.main.domain.Post
import org.example.project.feature.main.presentation.models.PostUi

fun Post.toPostUi(): PostUi {
    return PostUi(
        id = id,
        text = text,
        image = image,
        commentAmount = commentAmount,
        favoriteAmount = favoriteAmount
    )
}