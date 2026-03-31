package org.gulnazidr.stepik.core.network.ktor.catalog.source

import org.gulnazidr.stepik.core.model.StepikCatalog

interface RemoteCatalogRepository {
    suspend fun getCatalog(page: Int): Result<StepikCatalog>
}