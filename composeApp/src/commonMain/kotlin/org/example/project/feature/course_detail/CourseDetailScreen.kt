package org.example.project.feature.course_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.checkmark
import dvizexcursion.composeapp.generated.resources.clock
import dvizexcursion.composeapp.generated.resources.description_text
import dvizexcursion.composeapp.generated.resources.learning_format_text
import dvizexcursion.composeapp.generated.resources.refresh
import dvizexcursion.composeapp.generated.resources.requirements_text
import dvizexcursion.composeapp.generated.resources.skill
import dvizexcursion.composeapp.generated.resources.skill_acquire_text
import dvizexcursion.composeapp.generated.resources.start_lesson_text
import dvizexcursion.composeapp.generated.resources.target_audience_text
import dvizexcursion.composeapp.generated.resources.what_get_text
import org.example.project.core.designsystem.components.ErrorDialog
import org.example.project.core.designsystem.components.NavigationButton
import org.example.project.feature.main.presentation.CourseViewModel
import org.example.project.feature.main.presentation.models.CourseDetailUi
import org.example.project.feature.search.components.SearchTopAppBar
import org.example.project.presentation.components.CircleLoading
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CourseDetailsCard(
    onStartLessonClick: () -> Unit,
    navigateToMain: () -> Unit,
    courseViewModel: CourseViewModel = koinViewModel<CourseViewModel>()
) {
    val courseDetailFetchRes by courseViewModel.fetchedCourseResult.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                SearchTopAppBar(
                    onBack = navigateToMain,
                    onValueChanged = {
                        courseViewModel.onSearch(
                            it
                        )
                    }
                )
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    courseDetailFetchRes.Display(
                        onSuccess = { courseDetailUi ->
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                                    .verticalScroll(scrollState),
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = courseDetailUi.courseUi.title,
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Text(
                                        text = "${courseDetailUi.courseUi.price} ₽",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.padding(start = 12.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(Res.drawable.clock),
                                        tint = MaterialTheme.colorScheme.primary,
                                        contentDescription = "",
                                        modifier = Modifier.size(20.dp)
                                    )

                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = courseDetailUi.workloadTime,
                                        style = MaterialTheme.typography.bodyMedium
                                    )

                                    Spacer(modifier = Modifier.width(24.dp))

                                    Icon(
                                        painter = painterResource(Res.drawable.refresh),
                                        tint = MaterialTheme.colorScheme.primary,
                                        contentDescription = "",
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = courseDetailUi.difficultyLevel.capitalize(Locale.current),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = stringResource(Res.string.description_text),
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = courseDetailUi.courseUi.description,
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = stringResource(Res.string.target_audience_text),
                                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = courseDetailUi.targetAudience.replace("\\n", "\n"),
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = stringResource(Res.string.requirements_text),
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = courseDetailUi.requirements.replace(Regex("<.*?>"), ""),
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = stringResource(Res.string.skill_acquire_text),
                                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                courseDetailUi.acquiredSkills.forEach { skill ->
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            painter = painterResource(Res.drawable.skill),
                                            tint = MaterialTheme.colorScheme.primary,
                                            contentDescription = "",
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = skill,
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = stringResource(Res.string.what_get_text),
                                    style = MaterialTheme.typography.bodyLarge
                                        .copy(fontWeight = FontWeight.Medium)
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                courseDetailUi.acquiredAssets.forEach { asset ->
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            painter = painterResource(Res.drawable.checkmark),
                                            tint = MaterialTheme.colorScheme.primary,
                                            contentDescription = "",
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = asset,
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = stringResource(Res.string.learning_format_text),
                                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = courseDetailUi.learningFormat.replace(
                                        Regex("<.*?>"),
                                        ""
                                    ),
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Spacer(modifier = Modifier.height(24.dp))

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Lessons: ${courseDetailUi.lessonsCount}",
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                    NavigationButton(
                                        onBtnClick = onStartLessonClick,
                                        text = stringResource(Res.string.start_lesson_text)
                                    )
                                }
                            }
                        },
                        onError = { error ->
                            ErrorDialog(
                                errorMessage = error,
                                onRetry = {}
                            )
                        },
                        onLoading = {
                            CircleLoading()
                        }
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//private fun CourseDetailPrev(){
//    CourseDetailsCard(
//        workload = "2-3 часа в неделю",
//        targetAudience = "Школьники 5–11 классов.\nЮные блогеры и геймеры, которые хотят понимать, как работает их оборудование.\nВсе, кто готовится к урокам информатики или просто любит гаджеты.",
//        summary = "Как компьютер общается с миром? В этом курсе мы разберем всё: от обычной мышки до очков виртуальной реальности и промышленных датчиков. Узнаем, что такое порты, зачем нужны драйверы и как информация попадает из ваших рук прямо в процессор. Понятный гид по периферии для учеников 5–11 классов.",
//        requirements = "Нужно просто уметь пользоваться компьютером на уровне пользователя. Знание устройства системного блока будет плюсом, но не обязательно.",
//        difficulty = "easy",
//        acquiredSkills = listOf(
//            "Отличать устройства ввода от устройств вывода (и знать те, что умеют и то, и другое).",
//            "Разбираться в современных интерфейсах подключения (USB-C, HDMI, Thunderbolt).",
//            "Понимать, как работают сенсорные экраны, оптические мыши и биометрические сканеры.",
//            "Узнавать, зачем компьютеру нужны «переводчики» (драйверы) для общения с принтером или камерой.",
//            "Ориентироваться в мире внешних накопителей данных."
//        ),
//        acquiredAssets = listOf(
//            "Четкое понимание классификации компьютерного оборудования.",
//            "Навык подбора правильных кабелей и интерфейсов для разных задач.",
//            "Базовые знания о передаче данных, которые пригодятся в программировании и робототехнике.",
//            "Сертификат Stepik в ваше портфолио."
//        ),
//        learningFormat = "Курс состоит из 5 интерактивных уроков. В программе: наглядные схемы, видеоразборы, задания на классификацию устройств и тесты на понимание принципов их работы.",
//        title = "MOOC «Ввод-вывод и периферийные устройства в компьютерных системах»",
//        price = "3900.00",
//        lessonsCount = 17,
//        onStartLessonClick = {
//            // Navigate to lesson with ID 2264003 here
//        }
//    )
//}