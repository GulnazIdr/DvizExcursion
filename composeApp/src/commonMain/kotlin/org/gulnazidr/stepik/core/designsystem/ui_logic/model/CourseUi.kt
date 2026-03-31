package org.gulnazidr.stepik.core.designsystem.ui_logic.model

import org.gulnazidr.stepik.feature.auth.presentation.models.UserUi

data class CourseUi(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val price: Double,
    val commentAmount: Int,
    val favoriteAmount: Int,
    val learnersCount: Int,
    val authorList: List<UserUi>
)