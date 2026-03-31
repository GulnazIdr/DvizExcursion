package org.gulnazidr.stepik.core.network.ktor.catalog.impl

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.gulnazidr.stepik.core.domain.cancellationRunCatching
import org.gulnazidr.stepik.core.model.Catalog
import org.gulnazidr.stepik.core.model.CatalogContent
import org.gulnazidr.stepik.core.model.CourseDetail
import org.gulnazidr.stepik.core.model.StepikCatalog
import org.gulnazidr.stepik.core.network.CustomServerException
import org.gulnazidr.stepik.core.network.TokenRefreshException
import org.gulnazidr.stepik.core.network.ktor.catalog.model.CatalogContentDto
import org.gulnazidr.stepik.core.network.ktor.catalog.model.CatalogDto
import org.gulnazidr.stepik.core.network.ktor.catalog.model.StepikCatalogDto
import org.gulnazidr.stepik.core.network.ktor.catalog.source.RemoteCatalogRepository
import org.gulnazidr.stepik.core.network.mapper.MetaToPageInfoMapper
import org.gulnazidr.stepik.core.room.source.LocalCatalogRepository
import org.gulnazidr.stepik.core.domain.auth.TokenRepository
import org.gulnazidr.stepik.feature.course_catalog.domain.remote.RemoteCourseRepository

class RemoteCatalogRepositoryImpl(
    private val client: HttpClient,
    private val tokenRepository: TokenRepository,
    private val metaToPageInfoMapper: MetaToPageInfoMapper,
    private val remoteCourseRepository: RemoteCourseRepository,
    private val localCatalogRepository: LocalCatalogRepository
) : RemoteCatalogRepository{

    override suspend fun getCatalog(page: Int): Result<StepikCatalog> {
        return cancellationRunCatching {
            client.get(urlString = "catalog-blocks")
        }.map { response ->
            when (response.status.value) {
                401 -> {
                    val isRefreshed = tokenRepository.refreshToken()
                    if (!isRefreshed){
                        throw TokenRefreshException("failed to refresh token")
                    }else {
                        getCatalog(page)
                    }
                }
                in 500..511 -> throw CustomServerException("server error $response")
            }
            response.body<StepikCatalogDto>()
                .toStepikCatalog(metaToPageInfoMapper, remoteCourseRepository)
        }
    }
}


private suspend fun StepikCatalogDto.toStepikCatalog(
    metaToPageInfoMapper: MetaToPageInfoMapper,
    remoteCourseRepository: RemoteCourseRepository
): StepikCatalog {
    return StepikCatalog(
        pageInfo = metaToPageInfoMapper.map(meta),
        catalogs = catalogs.map { it.toCatalog(remoteCourseRepository) }
    )
}

private suspend fun CatalogDto.toCatalog(
    remoteCourseRepository: RemoteCourseRepository
): Catalog {
    return Catalog(
        id = id,
        title = title,
        content = content.map { it.toCatalogContent(remoteCourseRepository) }
    )
}

private suspend fun CatalogContentDto.toCatalogContent(
    remoteCourseRepository: RemoteCourseRepository
): CatalogContent {

    val courseRes = remoteCourseRepository.getCoursesByIds(courses)

    var courses = emptyList<CourseDetail>()

    courseRes.onSuccess { ktorStepikCourseDetailed ->
        courses = ktorStepikCourseDetailed.courses
    }.onFailure {
        courses = emptyList()
    }

    return CatalogContent(
        id = id,
        title = title,
        description = description,
        courses = courses
    )
}