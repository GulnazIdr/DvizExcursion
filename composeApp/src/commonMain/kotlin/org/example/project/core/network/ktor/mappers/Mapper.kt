package org.example.project.core.network.ktor.mappers

import io.github.aakira.napier.Napier
import kotlinx.serialization.json.jsonPrimitive
import org.example.project.core.network.model.CourseDetailDto
import org.example.project.core.network.model.CourseDto
import org.example.project.core.network.model.MetaDto
import org.example.project.core.network.model.StepikCourseDetailedDto
import org.example.project.core.network.model.StepikDto
import org.example.project.feature.main.domain.Course
import org.example.project.feature.main.domain.CourseDetail
import org.example.project.feature.main.domain.PageInfo
import org.example.project.feature.main.domain.Stepik
import org.example.project.feature.main.domain.StepikDetailed

fun CourseDto.toCourse(): Course{
    var  pr = 0.0
    try {
        pr = price?.jsonPrimitive?.content?.toDoubleOrNull() ?: 0.0
    }catch (e: IllegalArgumentException ){
        Napier.wtf("pricing2 $pr $e")
    }

    return Course(
        id = id,
        title = title,
        description = summary,
        image = cover ?: "",
        commentAmount = 0,
        favoriteAmount = 0,
        price = pr,
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
            learnersCount = lessons_count
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