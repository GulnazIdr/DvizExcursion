package org.example.project.feature.main.presentation.mappers

import org.example.project.feature.main.domain.Course
import org.example.project.feature.main.domain.CourseDetail
import org.example.project.feature.main.presentation.models.CourseDetailUi
import org.example.project.feature.main.presentation.models.CourseUi

fun Course.toCourseUi(): CourseUi {
    return CourseUi(
        id = id,
        title = title,
        description = description,
        image = image,
        commentAmount = commentAmount,
        favoriteAmount = favoriteAmount,
        price = price
    )
}

fun CourseDetail.toCourseDetailUi(): CourseDetailUi{
    return CourseDetailUi(
        courseUi = courseBaseInfo.toCourseUi(),
        workloadTime = workloadTime,
        targetAudience = targetAudience,
        requirements = requirements,
        difficultyLevel = difficultyLevel,
        acquiredSkills = acquiredSkills,
        acquiredAssets = acquiredAssets,
        learningFormat = learningFormat,
        lessonsCount = lessonsCount
    )
}