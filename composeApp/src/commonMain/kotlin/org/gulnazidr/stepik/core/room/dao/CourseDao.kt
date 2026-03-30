package org.gulnazidr.stepik.core.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import org.gulnazidr.stepik.core.room.model.CourseEntity

@Dao
interface CourseDao {
    @Upsert
    suspend fun setCourseList(courseList: List<CourseEntity>)

    @Update
    suspend fun updateCourse(courseDetailed: CourseEntity)

    @Query("SELECT * FROM course_table")
    suspend fun getCourseList(): List<CourseEntity>

    @Query("SELECT * FROM course_table where id = :id")
    suspend fun getCourseById(id: Int): CourseEntity?

    @Query("DELETE FROM course_table")
    suspend fun deleteCourses()
}