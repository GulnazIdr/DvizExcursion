package org.example.project.core.designsystem.ui_logic

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.example.project.core.common.result.FetchResult
import org.example.project.core.common.result.NetworkError
import org.example.project.core.database.LocalCourseRepository
import org.example.project.core.network.ktor.source.RemoteCourseRepository
import org.example.project.feature.course_catalog.presentation.mappers.toCourseDetail
import org.example.project.feature.course_catalog.presentation.mappers.toCourseDetailUi
import org.example.project.feature.course_catalog.presentation.models.CourseDetailUi
import org.example.project.core.designsystem.ui_logic.model.CourseUiState
import org.example.project.feature.course_catalog.presentation.result.FetchResultUi
import org.example.project.feature.course_catalog.presentation.result.FetchResultUi.Success
import org.example.project.core.model.CourseDetail
import org.example.project.core.model.Stepik

class FetchCoursesUseCase(
    private val remoteCourseRepository: RemoteCourseRepository,
    private val localCourseRepository: LocalCourseRepository
) {
    private var currentPage = 1
    private val _courseList: MutableStateFlow<List<CourseDetailUi>> = MutableStateFlow(
        value = emptyList()
    )

    val courseList: StateFlow<List<CourseDetailUi>> = _courseList

    suspend operator fun invoke(
        courseFetchedState: MutableStateFlow<CourseUiState>
    ) {
        courseFetchedState.update { state ->
            state.copy(
                isDataLoading = true
            )
        }

        val result = remoteCourseRepository.getCourses(currentPage)

        when (result) {
            is FetchResult.Success<Stepik> -> {
                if (result.successData.pageInfo.hasNext) {
                    currentPage++
                    _courseList.value += result.successData.courses.map { it.toCourseDetailUi() }

                    if (_courseList.value.isEmpty()) {
                        invoke(courseFetchedState)
                        return
                    }

                    courseFetchedState.update { state ->
                        state.copy(
                            courseFetchedResult = Success(data = _courseList.value),
                            isDataLoading = false,
                            isRefreshing = false
                        )
                    }

                    localCourseRepository.saveCourse(_courseList.value.map { it.toCourseDetail() })

                    if (_courseList.value.size < 20) {
                        invoke(courseFetchedState)
                        return
                    }

                } else {
                    courseFetchedState.update { state ->
                        state.copy(
                            isPageEnded = true,
                            isDataLoading = false,
                            isRefreshing = false
                        )
                    }
                }
            }

            is FetchResult.Cache<List<CourseDetail>, NetworkError> -> {
                courseFetchedState.update { state ->
                    state.copy(
                        courseFetchedResult = FetchResultUi.Cached(
                            cacheData = result.cacheData.map { it.toCourseDetailUi() },
                            reason = result.cacheError.asUiText()
                        ),
                        isDataLoading = false,
                        isRefreshing = false
                    )
                }
            }
        }
    }
}