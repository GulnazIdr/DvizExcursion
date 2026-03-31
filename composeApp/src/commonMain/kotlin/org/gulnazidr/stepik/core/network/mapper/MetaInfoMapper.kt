package org.gulnazidr.stepik.core.network.mapper

import org.gulnazidr.stepik.core.common.result.Mapper
import org.gulnazidr.stepik.core.model.PageInfo
import org.gulnazidr.stepik.core.network.ktor.models.MetaDto

class MetaToPageInfoMapper: Mapper<MetaDto, PageInfo>{
    override fun map(item: MetaDto): PageInfo {
        return PageInfo(
            page = item.page,
            hasNext = item.hasNext,
            hasPrevious = item.hasPrevious
        )
    }
}