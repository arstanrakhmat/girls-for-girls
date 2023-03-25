package com.example.girls4girls.presentation.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.girls4girls.data.model.Message
import com.example.girls4girls.data.model.ResetPasswordConfirmResponse
import com.example.girls4girls.data.repository.ResetPasswordRepository
import kotlinx.coroutines.launch

class ResetPasswordViewModel(private val repository: ResetPasswordRepository) : ViewModel() {

    val success = MutableLiveData<Message>()
    val errorMessage = MutableLiveData<String>()

    val token = MutableLiveData<ResetPasswordConfirmResponse>()

    fun resetPasswordEmail(email: String) {
        viewModelScope.launch {
            val response = repository.resetPasswordEmail(email)

            if (response.isSuccessful) {
                success.postValue(response.body())
            } else {
                errorMessage.postValue(response.errorBody().toString())
            }
        }
    }

    fun resetPasswordEmailConfirm(email: String, code: String) {
        viewModelScope.launch {
            val response = repository.resetPasswordEmailConfirm(email, code)

            if (response.isSuccessful) {
                token.postValue(response.body())
            } else {
                errorMessage.postValue(response.errorBody().toString())
            }
        }
    }

    fun changePassword(token: String?, newPassword: String) {
        viewModelScope.launch {
            val response = repository.changePassword(token, newPassword)

            if (response.isSuccessful) {
                success.postValue(response.body())
            } else {
                errorMessage.postValue(response.errorBody().toString())
            }
        }
    }
}