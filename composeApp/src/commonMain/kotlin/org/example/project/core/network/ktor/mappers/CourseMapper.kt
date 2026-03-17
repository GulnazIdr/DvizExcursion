package org.example.project.core.network.ktor.mappers

import org.example.project.core.model.CourseDetailDto
import org.example.project.core.model.CourseDto
import org.example.project.core.model.Course
import org.example.project.core.model.CourseDetail

fun CourseDto.toCourse(): Course{
    return Course(
        id = id,
        title = title,
        description = summary,
        image = cover ?: "",
        commentAmount = 0,
        favoriteAmount = 0,
        price = price?.toDoubleOrNull() ?: 0.0,
        learnersCount = learners_count
    )
}

fun CourseDetailDto.toCourseDetail(): CourseDetail{
    return CourseDetail(
        courseBaseInfo = Course(
            id = id,
            title = title,
            description = summary,
            image = cover ?: "",
            commentAmount = 0,
            favoriteAmount = 0,
            price = price?.toDoubleOrNull() ?: 0.0,
            learnersCount = learnersCount
        ),
        workloadTime = workload,
        targetAudience = targetAudience,
        requirements = requirements,
        difficultyLevel = difficulty.orEmpty(),
        acquiredSkills = acquiredSkills,
        acquiredAssets = acquiredAssets,
        learningFormat = learningFormat,
        lessonsCount = lessonsCount
    )
}