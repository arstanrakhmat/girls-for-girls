package com.example.girls4girls.presentation.question

import androidx.lifecycle.ViewModel
import com.example.girls4girls.data.Question

class QuestionViewModel: ViewModel() {

    val questions = listOf<Question>()

//    val questions = listOf(
//        Question("Girls4Girls - это в первую очередь...",
//            Option(listOf("Образовательная программа",
//                "Группа ВК",
//                "Музыкальный коллектив",
//                "Государственый проект")),
//        Question("Когда был основан Girls4Girls?",
//            listOf("2021",
//                "2022",
//                "2020",
//                "2019")),
//        Question("С каких областей начался Girls4Girls?",
//            listOf("Джалал-Абад и Ош",
//                "Чуй и Ош",
//                "Баткен и Ош",
//                "Чуй и Ысык-Көл"))
//    )
}