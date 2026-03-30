package org.gulnazidr.stepik.core.room.source

import org.gulnazidr.stepik.core.model.StepikCatalog

interface LocalCatalogRepository {
    suspend fun getCatalogList(): Result<StepikCatalog>
}