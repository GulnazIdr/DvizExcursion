package org.example.project.core.designsystem.ui_logic.mapper

import org.example.project.core.common.result.Mapper
import org.example.project.core.model.Course
import org.example.project.core.model.CourseDetail
import org.example.project.feature.course_catalog.presentation.models.CourseDetailUi
import org.example.project.feature.course_catalog.presentation.models.CourseUi

class CourseUiMapper() : Mapper<Course, CourseUi> {
    override fun map(item: Course): CourseUi {
        return CourseUi(
            id = item.id,
            title = item.title,
            description = item.description,
            image = item.image,
            commentAmount = item.commentAmount,
            favoriteAmount = item.favoriteAmount,
            price = item.price,
            learnersCount = item.learnersCount
        )
    }
}

class CourseToCourseDetailUiMapper() : Mapper<Course, CourseDetailUi> {
    override fun map(item: Course): CourseDetailUi {
        return CourseDetailUi(
            courseUi = CourseUi(
                id = item.id,
                title = item.title,
                description = item.description,
                image = item.image,
                commentAmount = item.commentAmount,
                favoriteAmount = item.favoriteAmount,
                price = item.price,
                learnersCount = item.learnersCount
            )
        )
    }
}

class CourseDetailToCourseDetailUiMapper(
    val courseUiMapper: CourseUiMapper
) : Mapper<CourseDetail, CourseDetailUi> {
    override fun map(item: CourseDetail): CourseDetailUi {
        return CourseDetailUi(
            courseUi = courseUiMapper.map(item.courseBaseInfo),
            workloadTime = item.workloadTime,
            targetAudience = item.targetAudience,
            requirements = item.requirements,
            difficultyLevel = item.difficultyLevel,
            acquiredSkills = item.acquiredSkills,
            acquiredAssets = item.acquiredAssets,
            learningFormat = item.learningFormat,
            lessonsCount = item.lessonsCount
        )
    }
}