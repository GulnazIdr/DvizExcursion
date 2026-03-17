package org.example.project.core.network.ktor.repository

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.SerializationException
import org.example.project.core.common.result.FetchResult
import org.example.project.core.common.result.NetworkError
import org.example.project.core.common.result.codeMapper
import org.example.project.core.database.LocalCourseRepository
import org.example.project.core.network.ktor.source.RemoteCourseRepository
import org.example.project.core.model.StepikCourseDetailedDto
import org.example.project.core.model.StepikDto
import org.example.project.core.network.ktor.HttpRoutes
import org.example.project.core.network.ktor.mappers.toStepik
import org.example.project.core.model.CourseDetail
import org.example.project.core.model.Stepik
import org.example.project.core.model.StepikDetailed
import java.net.UnknownHostException

class RemoteCourseRepositoryImpl(
    private val client: HttpClient,
    private val localCourseRepository: LocalCourseRepository
) : RemoteCourseRepository {
    override suspend fun getCourses(page: Int): FetchResult<Stepik, NetworkError, List<CourseDetail>> {
        val response = try {
            client.get(urlString = HttpRoutes.COURSES) { parameter("page", page) }
        } catch (e: UnknownHostException) {
            Napier.e("UnknownHostException error: ${e.message}")
            return FetchResult.Cache(
                localCourseRepository.getCourses(), NetworkError.NO_INTERNET
            )
        } catch (e: SerializationException) {
            Napier.e("SerializationException error: ${e.message}")
            return FetchResult.Cache(
                localCourseRepository.getCourses(), NetworkError.SERIALIZATION
            )
        }

        return response.codeMapper<StepikDto, Stepik, List<CourseDetail>>(
            transformData = { it.toStepik() },
            getCache = { localCourseRepository.getCourses() }
        )
    }

    override suspend fun getCourseById(id: Int): FetchResult<StepikDetailed, NetworkError, CourseDetail?> {
        val response = try {
            client.get(urlString = HttpRoutes.COURSE_DETAILS + "$id")
        } catch (e: UnknownHostException) {
            Napier.e("UnknownHostException error: ${e.message}")
            return FetchResult.Cache(
                localCourseRepository.getCourseById(id), NetworkError.NO_INTERNET
            )
        } catch (e: SerializationException) {
            Napier.e("SerializationException error: ${e.message}")
            return FetchResult.Cache(
                localCourseRepository.getCourseById(id), NetworkError.SERIALIZATION
            )
        }

        return response.codeMapper<StepikCourseDetailedDto, StepikDetailed, CourseDetail?>(
            transformData = { it.toStepik() },
            getCache = { localCourseRepository.getCourseById(id) }
        )
    }
}