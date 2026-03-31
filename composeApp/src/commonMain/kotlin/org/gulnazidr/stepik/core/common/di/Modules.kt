package org.gulnazidr.stepik.core.common.di

import org.gulnazidr.stepik.core.designsystem.ui_logic.mapper.CourseDetailToCourseDetailUiMapper
import org.gulnazidr.stepik.core.designsystem.ui_logic.mapper.CourseToCourseDetailUiMapper
import org.gulnazidr.stepik.core.designsystem.ui_logic.mapper.CourseUiMapper
import org.gulnazidr.stepik.core.designsystem.ui_logic.mapper.UserToUserUiMapper
import org.gulnazidr.stepik.core.designsystem.ui_logic.mapper.UserUiToUserMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
val courseMapperModule = module {
    factoryOf ( ::CourseUiMapper )
    factoryOf( ::CourseToCourseDetailUiMapper )
    factoryOf ( ::CourseDetailToCourseDetailUiMapper )
}

val userMapperModule = module {
    factoryOf ( ::UserUiToUserMapper )
    factoryOf ( ::UserToUserUiMapper )
}