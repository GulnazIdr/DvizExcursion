package org.example.project.core.database.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "course_table",
    indices = [
        Index(value = ["id"]),
    ],
)
data class CourseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val description: String,
    val image: String,
    val commentAmount: Int,
    val favoriteAmount: Int,
    val price: Double,
    val learnersCount: Int,

    val workloadTime: String = "",
    val targetAudience: String = "",
    val requirements: String = "",
    val difficultyLevel: String = "",
    val acquiredSkills: List<String> = emptyList(),
    val acquiredAssets: List<String> = emptyList(),
    val learningFormat: String = "",
    val lessonsCount: Int = 0,
)