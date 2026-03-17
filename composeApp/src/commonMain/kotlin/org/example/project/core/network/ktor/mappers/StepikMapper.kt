package org.example.project.core.network.ktor.mappers

import org.example.project.core.model.MetaDto
import org.example.project.core.model.StepikCourseDetailedDto
import org.example.project.core.model.StepikDto
import org.example.project.core.model.PageInfo
import org.example.project.core.model.Stepik
import org.example.project.core.model.StepikDetailed


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
        hasNext = hasNext,
        hasPrevious = hasPrevious
    )
}