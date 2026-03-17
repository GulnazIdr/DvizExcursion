package org.example.project.core.database.mappers

import org.example.project.core.database.model.CourseEntity
import org.example.project.core.model.Course
import org.example.project.core.model.CourseDetail

fun CourseDetail.toCourseEntity(): CourseEntity{
    return CourseEntity(
        id = courseBaseInfo.id,
        title = courseBaseInfo.title,
        description = courseBaseInfo.description,
        image = courseBaseInfo.image,
        commentAmount = courseBaseInfo.commentAmount,
        favoriteAmount = courseBaseInfo.favoriteAmount,
        price = courseBaseInfo.price,
        learnersCount = courseBaseInfo.learnersCount,
        workloadTime = workloadTime,
        targetAudience = targetAudience,
        requirements = requirements,
        difficultyLevel = difficultyLevel,
        acquiredSkills = acquiredSkills,
        acquiredAssets = acquiredAssets,
        learningFormat = learningFormat,
        lessonsCount = lessonsCount,
    )
}

fun CourseEntity.toCourse(): Course{
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

fun Course.toCourseEntity(): CourseEntity{
    return CourseEntity(
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

fun CourseEntity.toCourseDetail(): CourseDetail{
    return CourseDetail(
        courseBaseInfo = this.toCourse(),
        workloadTime = workloadTime,
        targetAudience = targetAudience,
        requirements = requirements,
        difficultyLevel = difficultyLevel,
        acquiredSkills = acquiredSkills,
        acquiredAssets = acquiredAssets,
        learningFormat = learningFormat,
        lessonsCount = lessonsCount,
    )
}