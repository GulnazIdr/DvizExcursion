package org.example.project.feature.main.data.mappers

import org.example.project.feature.main.data.dto.CourseDetailDto
import org.example.project.feature.main.data.dto.CourseDto
import org.example.project.feature.main.data.dto.MetaDto
import org.example.project.feature.main.data.dto.StepikCourseDetailedDto
import org.example.project.feature.main.data.dto.StepikDto
import org.example.project.feature.main.domain.Course
import org.example.project.feature.main.domain.CourseDetail
import org.example.project.feature.main.domain.PageInfo
import org.example.project.feature.main.domain.Stepik
import org.example.project.feature.main.domain.StepikDetailed

fun CourseDto.toCourse(): Course{
    return Course(
        id = id,
        title = title,
        description = summary,
        image = cover ?: "",
        commentAmount = 0,
        favoriteAmount = 0,
        price = price?.toIntOrNull() ?: 0
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
            price = price?.toIntOrNull() ?: 0
        ),
        workloadTime = workload,
        targetAudience = target_audience,
        requirements = requirements,
        difficultyLevel = difficulty ?: "",
        acquiredSkills = acquired_skills,
        acquiredAssets = acquired_assets,
        learningFormat = learning_format,
        lessonsCount = lessons_count
    )
}

fun StepikDto.toStepik(): Stepik{
    return Stepik(
        pageInfo = meta.toPageInfo(),
        courses = courses.map { it.toCourse() }
    )
}

fun StepikCourseDetailedDto.toStepik(): StepikDetailed{
    return StepikDetailed(
        pageInfo = meta.toPageInfo(),
        courses = courses.map { it.toCourseDetail() }
    )
}

fun MetaDto.toPageInfo(): PageInfo{
    return PageInfo(
        page = page,
        hasNext = has_next,
        hasPrevious = has_previous
    )
}