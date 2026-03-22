package org.example.project.core.common.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.project.core.designsystem.ui_logic.mapper.CourseDetailToCourseDetailUiMapper
import org.example.project.core.designsystem.ui_logic.mapper.CourseToCourseDetailUiMapper
import org.example.project.core.designsystem.ui_logic.mapper.CourseUiMapper
import org.example.project.core.domain.FetchCoursesUseCase
import org.example.project.feature.course_catalog.CourseViewModel
import org.example.project.feature.course_detail.domain.FetchCourseDetailUseCase
import org.example.project.feature.course_detail.presentation.CourseDetailViewModel
import org.example.project.feature.search.SearchViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val courseModule = module {
    viewModel {
        CourseViewModel(
            fetchCoursesUseCase = get(),
            courseToCourseDetailUiMapper = get<CourseToCourseDetailUiMapper>(),
            courseDetailUi = get<CourseDetailToCourseDetailUiMapper>()
        )
    }
}

val courseUseCaseModule = module {
    singleOf(::FetchCoursesUseCase)
}

val searchModule = module {
    viewModelOf(::SearchViewModel)
}

val courseDetailModule = module {
    viewModel { (courseId: Int) ->
        CourseDetailViewModel(
            courseId = courseId,
            fetchCourseDetailUseCase = get(),
            courseDetailToCourseDetailUiMapper = get()
        )
    }
    factoryOf(::FetchCourseDetailUseCase)
}

val courseMapperModule = module {
    factory { CourseUiMapper() }
    factory { CourseToCourseDetailUiMapper() }
    factory { CourseDetailToCourseDetailUiMapper(get()) }
}

val httpClientModule = module {
    single<HttpClientEngine>{
        OkHttp.create()
    }

    single<HttpClient>{
        HttpClient(get<HttpClientEngine>()){
            expectSuccess = false

            install(Logging){
                level = LogLevel.ALL
            }

            followRedirects = true
            install(ContentNegotiation){
                json(
                    json = Json{
                        ignoreUnknownKeys = true
                        coerceInputValues = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
}

expect val authModule: Module