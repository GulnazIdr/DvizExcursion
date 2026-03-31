package org.gulnazidr.stepik.core.room.impl

import io.github.aakira.napier.Napier
import org.gulnazidr.stepik.core.domain.cancellationRunCatching
import org.gulnazidr.stepik.core.model.Course
import org.gulnazidr.stepik.core.model.CourseDetail
import org.gulnazidr.stepik.core.model.PageInfo
import org.gulnazidr.stepik.core.model.StepikCourse
import org.gulnazidr.stepik.core.model.StepikCourseDetailed
import org.gulnazidr.stepik.core.room.CustomRoomException
import org.gulnazidr.stepik.core.room.dao.CourseDao
import org.gulnazidr.stepik.core.room.model.CourseEntity
import org.gulnazidr.stepik.core.room.source.RoomCourseRepository

class RoomCourseRepositoryImpl(
    private val courseDao: CourseDao
): RoomCourseRepository {
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
        return cancellationRunCatching {
            val courses = courseDao.getCourseList()
            if (courses.isEmpty()){
                throw CustomRoomException("cache courses is empty")
            }else {
                courses.toStepik()
            }
        }
    }

    override suspend fun getCourseById(id: Int): Result<StepikCourseDetailed> {
        return cancellationRunCatching {
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

    override suspend fun getCoursesByIds(idList: List<Int>): Result<StepikCourseDetailed> {
        val course = mutableListOf<CourseEntity>()

        idList.forEach { id ->
            cancellationRunCatching {
                course += courseDao.getCourseById(id)
                    ?: throw CustomRoomException("cache course with $id is not found")
            }
        }

        val stepikDetailed = course.toStepikDetailed()

        return Result.success(stepikDetailed)
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

private fun List<CourseEntity>.toStepikDetailed(): StepikCourseDetailed {
    return StepikCourseDetailed(
        pageInfo = PageInfo(page = 1, hasNext = false, hasPrevious = false),
        courses = this.map { it.toCourseDetail() }
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