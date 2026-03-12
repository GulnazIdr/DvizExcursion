package org.example.project.core.network.ktor

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.project.core.common.result.FetchResult
import org.example.project.core.common.result.NetworkError
import org.example.project.core.common.result.codeMapper
import org.example.project.core.network.model.StepikCourseDetailedDto
import org.example.project.core.network.model.StepikDto
import org.example.project.core.network.ktor.mappers.toStepik
import org.example.project.feature.main.domain.StepikApi
import org.example.project.feature.main.domain.Stepik
import org.example.project.feature.main.domain.StepikDetailed

class StepikApiImpl (
    private val client: HttpClient
): StepikApi {
    override suspend fun getCourses(page: Int): FetchResult<Stepik, NetworkError> {
        val response = try {
            client.get(urlString = HttpRoutes.COURSES){
                parameter("page", page)
            }
        } catch(e: RedirectResponseException) {
            // 3xx - responses
            Napier.wtf("RedirectResponse error: ${e.response.status.description}")
            null
        } catch(e: ClientRequestException) {
            // 4xx - responses
            Napier.wtf("ClientRequest error: ${e.response.status.description}")
            null
        } catch(e: ServerResponseException) {
            // 5xx - responses
            Napier.wtf("ServerResponse error: ${e.response.status.description}")
            null
        } catch(e: Exception) {
            Napier.wtf("Error: ${e.message}")
            null
        }

        return response.codeMapper<StepikDto, Stepik>(
            transform = {it.toStepik()}
        )
    }

    override suspend fun getCourseById(id: Int): FetchResult<StepikDetailed, NetworkError> {
        val response = try {
            client.get(urlString = HttpRoutes.COURSE_DETAILS+"$id")
        } catch(e: RedirectResponseException) {
            // 3xx - responses
            Napier.wtf("Error: ${e.response.status.description}")
            null
        } catch(e: ClientRequestException) {
            // 4xx - responses
            Napier.wtf("Error: ${e.response.status.description}")
            null
        } catch(e: ServerResponseException) {
            // 5xx - responses
            Napier.wtf("Error: ${e.response.status.description}")
            null
        } catch(e: Exception) {
            Napier.wtf("Error: ${e.message}")
            null
        }

        return response.codeMapper<StepikCourseDetailedDto, StepikDetailed>(
            transform = {it.toStepik()}
        )
    }
}