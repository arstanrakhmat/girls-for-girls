package com.example.girls4girls.data.model

//interface QuestionnaireAnswer {
//    val questionId: Int
//    val type: AnswerType
//}

enum class AnswerType(val type: String) {
    TEXT("TEXT"),
    CHECK_BOX("CHECK_BOX"),
    VARIANTS("VARIANTS")
}

//data class TextAnswer(
//    override val questionId: Int,
//    override val type: AnswerType,
//    var text: String
//) : QuestionnaireAnswer
//
//data class VariantsAnswer(
//    override val questionId: Int,
//    override val type: AnswerType,
//    val answerIndex: Int
//) : QuestionnaireAnswer
//
//data class CheckBoxAnswer(
//    override val questionId: Int,
//    override val type: AnswerType,
//    val multipleChoices: List<Int>
//) : QuestionnaireAnswer
