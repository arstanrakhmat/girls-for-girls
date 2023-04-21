package com.example.girls4girls.data

import android.os.Parcelable
import com.example.girls4girls.presentation.question.QuestionFragment
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Answer (
    val question: QuestionFragment.OldQuestion,
    val chosenId: Int,
    val isCorrect: Boolean
): Parcelable