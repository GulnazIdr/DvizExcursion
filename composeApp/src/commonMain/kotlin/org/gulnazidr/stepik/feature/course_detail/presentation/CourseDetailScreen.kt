package org.gulnazidr.stepik.feature.course_detail.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.gulnazidr.stepik.core.designsystem.components.BasicTopAppBar
import org.gulnazidr.stepik.core.designsystem.components.CircleLoading
import org.gulnazidr.stepik.core.designsystem.components.ErrorDialog
import org.gulnazidr.stepik.feature.course_detail.presentation.components.CourseDetailContent
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CourseDetailsCard(
    courseId: Int,
    onStartLessonClick: () -> Unit,
    navigateToMain: () -> Unit,
    courseViewModel: CourseDetailViewModel = koinViewModel<CourseDetailViewModel>()
) {
    var isDismissed by rememberSaveable{ mutableStateOf(false) }
    val courseDetailFetchRes by courseViewModel.currentCourseState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Column{
        BasicTopAppBar(
            onBack = navigateToMain
        )

        Spacer(modifier = Modifier.height(10.dp))

        Card(
            modifier = Modifier.fillMaxSize(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.outlineVariant
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            courseDetailFetchRes.courseState.Display(
                onSuccess = { courseDetailUi ->
                    CourseDetailContent(
                        courseDetailUi = courseDetailUi,
                        scrollState = scrollState,
                        onStartLessonClick = onStartLessonClick,
                        isRefreshing = courseDetailFetchRes.isRefreshing,
                        onRefresh = { courseViewModel.refresh(courseId) }
                    )
                },
                onError = { error ->
                    ErrorDialog(
                        errorMessage = error,
                        onRetry = { courseViewModel.refresh(courseId) },
                        onClose = { isDismissed = true }
                    )
                },
                isDismissed = isDismissed,
                onLoading = {
                    CircleLoading()
                }
            )
        }
    }
}