package org.example.project.core.database.impl

import io.github.aakira.napier.Napier
import org.example.project.core.database.CustomRoomException
import org.example.project.core.database.dao.CourseDao
import org.example.project.core.database.model.CourseEntity
import org.example.project.core.database.source.LocalCourseRepository
import org.example.project.core.model.Course
import org.example.project.core.model.CourseDetail
import org.example.project.core.model.PageInfo
import org.example.project.core.model.StepikCourse
import org.example.project.core.model.StepikCourseDetailed

class LocalCourseRepositoryImpl (
    private val courseDao: CourseDao
): LocalCourseRepository{
    override suspend fun saveCourses(courseList: List<CourseDetail>): Boolean{
        return try {
            courseDao.setCourseList(courseList.map { it.toCourseEntity()})
            true
        }catch (e: Exception){
            Napier.e("saving courses error: $e")
            false
        }
    }

    override suspend fun getCourses(): Result<StepikCourse>{
        return runCatching {
            val courses = courseDao.getCourseList()
            if (courses.isEmpty()){
                throw CustomRoomException("cache courses is empty")
            }else {
                courses.toStepik()
            }
        }
    }

    override suspend fun getCourseById(id: Int): Result<StepikCourseDetailed> {
        return runCatching {
            val course = courseDao.getCourseById(id)
            course?.toStepikDetailed() ?: throw CustomRoomException("cache courses is empty")
        }
    }

    override suspend fun updateCourseDetailed(courseDetail: CourseDetail): Boolean{
        return try {
            courseDao.updateCourse(courseDetail.toCourseEntity())
            true
        }catch (e: Exception){
            Napier.e("updating course error: $e")
            false
        }
    }

    override suspend fun deleteCourse(): Boolean{
        return try {
            courseDao.deleteCourses()
            true
        }catch (e: Exception){
            Napier.e("updating course error: $e")
            false
        }
    }
}

private fun CourseDetail.toCourseEntity(): CourseEntity{
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

private fun List<CourseEntity>.toStepik(): StepikCourse {
    return StepikCourse(
        pageInfo = PageInfo(page = 1, hasNext = false, hasPrevious = false),
        courses = this.map { it.toCourse() }
    )
}

private fun CourseEntity.toStepikDetailed(): StepikCourseDetailed {
    return StepikCourseDetailed(
        pageInfo = PageInfo(page = 1, hasNext = false, hasPrevious = false),
        courses = listOf(this.toCourseDetail())
    )
}

private fun CourseEntity.toCourse(): Course {
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

private fun CourseEntity.toCourseDetail(): CourseDetail{
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