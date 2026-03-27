package org.example.project.core.network.mapper

import org.example.project.core.common.result.Mapper
import org.example.project.core.model.PageInfo
import org.example.project.core.network.ktor.models.MetaDto

class MetaToPageInfoMapper: Mapper<MetaDto, PageInfo>{
    override fun map(item: MetaDto): PageInfo {
        return PageInfo(
            page = item.page,
            hasNext = item.hasNext,
            hasPrevious = item.hasPrevious
        )
    }
}