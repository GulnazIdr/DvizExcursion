package org.example.project.feature.course_catalog.presentation.mappers

import org.example.project.feature.course_catalog.presentation.models.CourseDetailUi
import org.example.project.feature.course_catalog.presentation.models.CourseUi
import org.example.project.core.model.Course
import org.example.project.core.model.CourseDetail

fun Course.toCourseUi(): CourseUi {
    return CourseUi(
        id = id,
        title = title,
        description = description,
        image = image,
        commentAmount = commentAmount,
        favoriteAmount = favoriteAmount,
        price = price,
        learnersCount = learnersCount
    )
}

fun Course.toCourseDetailUi(): CourseDetailUi {
    return CourseDetailUi(
        courseUi = this.toCourseUi()
    )
}

fun CourseUi.toCourse(): Course {
    return Course(
        id = id,
        title = title,
        description = description,
        image = image,
        commentAmount = commentAmount,
        favoriteAmount = favoriteAmount,
        price = price,
        learnersCount = learnersCount
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

fun CourseDetailUi.toCourseDetail(): CourseDetail{
    return CourseDetail(
        courseBaseInfo = courseUi.toCourse(),
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