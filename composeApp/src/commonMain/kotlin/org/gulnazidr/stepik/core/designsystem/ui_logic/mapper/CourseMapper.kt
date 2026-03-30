package org.gulnazidr.stepik.core.designsystem.ui_logic.mapper

import org.gulnazidr.stepik.core.common.result.Mapper
import org.gulnazidr.stepik.core.model.Course
import org.gulnazidr.stepik.core.model.CourseDetail
import org.gulnazidr.stepik.core.designsystem.ui_logic.model.CourseDetailUi
import org.gulnazidr.stepik.core.designsystem.ui_logic.model.CourseUi

class CourseUiMapper(
    private val userToUserUiMapper: UserToUserUiMapper
) : Mapper<Course, CourseUi> {
    override fun map(item: Course): CourseUi {
        return CourseUi(
            id = item.id,
            title = item.title,
            description = item.description,
            image = item.image,
            commentAmount = item.commentAmount,
            favoriteAmount = item.favoriteAmount,
            price = item.price,
            learnersCount = item.learnersCount,
            authorList = item.authorList.map(userToUserUiMapper::map)
        )
    }
}

class CourseToCourseDetailUiMapper(
    private val userToUserUiMapper: UserToUserUiMapper
) : Mapper<Course, CourseDetailUi> {
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
                learnersCount = item.learnersCount,
                authorList = item.authorList.map(userToUserUiMapper::map)
            )
        )
    }
}

class CourseDetailToCourseDetailUiMapper(
    private val courseUiMapper: CourseUiMapper
) : Mapper<CourseDetail, CourseDetailUi> {
    override fun map(item: CourseDetail): CourseDetailUi {
        return CourseDetailUi(
            courseUi = courseUiMapper.map(item.courseBaseInfo),
            workloadTime = item.workloadTime,
            targetAudience = item.targetAudience.replace(
                "\\n", "\n"
            ),
            requirements = item.requirements.replace(Regex("<.*?>"), "")
                .replace(Regex("-"), ""),
            difficultyLevel = item.difficultyLevel,
            acquiredSkills = item.acquiredSkills.map { skill ->
                skill.replace(
                    Regex("—"), ""
                )
            },
            acquiredAssets = item.acquiredAssets,
            learningFormat = item.learningFormat.replace(
                Regex("<.*?>"),
                ""
            ),
            lessonsCount = item.lessonsCount
        )
    }
}