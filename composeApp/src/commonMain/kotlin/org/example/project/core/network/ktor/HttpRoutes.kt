package org.example.project.core.network.ktor

object HttpRoutes {
    private const val BASE_URL = "https://stepik.org/api"
    const val COURSES = "$BASE_URL/courses"
    const val COURSE_DETAILS = "$BASE_URL/courses/"

    const val CURRENT_USER_PROFILE = "$BASE_URL/stepics/1"
    const val EMAILS = "$BASE_URL/email-addresses"
}