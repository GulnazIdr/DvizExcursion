package org.example.project.core.database

import io.github.aakira.napier.Napier
import org.example.project.core.database.dao.CourseDao
import org.example.project.core.database.mappers.toCourseDetail
import org.example.project.core.database.mappers.toCourseEntity
import org.example.project.core.model.Course
import org.example.project.core.model.CourseDetail

class LocalCourseRepository (
    private val courseDao: CourseDao
){
    suspend fun saveCourse(courseList: List<CourseDetail>): Boolean{
        return try {
            courseDao.setCourseList(courseList.map { it.toCourseEntity()})
            true
        }catch (e: Exception){
            Napier.e("saving courses error: $e")
            false
        }
    }

    suspend fun getCourses(): List<CourseDetail>{
        return try {
            courseDao.getCourseList().map { it.toCourseDetail() }
        }catch (e: Exception){
            Napier.e("getting courses error: $e")
            emptyList()
        }
    }

    suspend fun getCourseById(id: Int): CourseDetail?{
        return try {
            courseDao.getCourseById(id)?.toCourseDetail()
        }catch (e: Exception){
            Napier.e("getting courses by id error: $e")
            null
        }
    }

    suspend fun updateCourseDetailed(courseDetail: CourseDetail): Boolean{
        return try {
            courseDao.updateCourse(courseDetail.toCourseEntity())
            true
        }catch (e: Exception){
            Napier.e("updating course error: $e")
            false
        }
    }

    suspend fun deleteCourse(): Boolean{
        return try {
            courseDao.deleteCourses()
            true
        }catch (e: Exception){
            Napier.e("updating course error: $e")
            false
        }
    }
}