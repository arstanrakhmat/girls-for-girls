package com.example.girls4girls.presentation.question

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.girls4girls.data.model.Jeton
import com.example.girls4girls.data.repository.QuizRepository
import com.example.girls4girls.presentation.videoblog.VideoblogFragment.Companion.TAG
import kotlinx.coroutines.launch

class ReviewViewModel(
    private val quizRepository: QuizRepository
): ViewModel() {

    val _jeton = MutableLiveData<Jeton>()

    fun getJeton(token: String?){
        viewModelScope.launch {
            val response = quizRepository.getJeton(token)

            if(response.isSuccessful){
                _jeton.postValue(response.body())
            } else {
                Log.d(TAG, "getJeton: ${response.errorBody().toString()}")
            }
        }
    }

}