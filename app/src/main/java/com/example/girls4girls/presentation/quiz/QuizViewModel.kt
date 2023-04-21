package com.example.girls4girls.presentation.quiz

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.girls4girls.data.Quiz
import com.example.girls4girls.data.repository.QuizRepository
import com.example.girls4girls.presentation.videoblog.VideoblogFragment.Companion.TAG
import kotlinx.coroutines.launch

class QuizViewModel(private val repository: QuizRepository): ViewModel() {

    val quiz = MutableLiveData<Quiz>()

    fun getQuiz(id: Long){
        viewModelScope.launch {
            val response = repository.getQuiz(id)

            if (response.isSuccessful){
                quiz.postValue(response.body()!!)
                Log.d(TAG, "succ getQuiz:${response.body()} ")
            } else {
                Log.d(TAG, "err getQuiz: ${response.errorBody().toString()}")
            }
        }
    }

}