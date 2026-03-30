package org.gulnazidr.stepik.core.network.ktor.comments.source

import org.gulnazidr.stepik.core.model.StepikCourse
import org.gulnazidr.stepik.core.network.ktor.models.DataWrapping

interface RemoteCommentRepository {
    suspend fun getComments(): Result<DataWrapping<StepikCourse>>

}