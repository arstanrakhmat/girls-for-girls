package com.example.girls4girls.presentation.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.girls4girls.data.model.UserActivate
import com.example.girls4girls.data.model.UserLogin
import com.example.girls4girls.data.model.UserRegistration
import com.example.girls4girls.data.model.UserRegistrationResponse
import com.example.girls4girls.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    val registered = MutableLiveData<UserRegistrationResponse>()
    val errorMessage = MutableLiveData<String>()



    fun userSignUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        phoneNumber: String
    ) {
        viewModelScope.launch {
            val response = repository.userSignUp(
                UserRegistration(
                    email,
                    password,
                    firstName,
                    lastName,
                    phoneNumber,
                )
            )

            if (response.isSuccessful) {
                registered.postValue(response.body())
            } else {
                errorMessage.postValue(response.errorBody().toString())
            }
        }
    }

    fun userActivate(
        email: String,
        phoneNumber: String,
        code: String
    ) {
        viewModelScope.launch {
            val response = repository.userActivate(
                UserActivate(
                    email,
                    phoneNumber,
                    code
                )
            )

            if (response.isSuccessful) {
                registered.postValue(response.body())
            } else {
                errorMessage.postValue(response.errorBody().toString())
            }
        }
    }

    fun userLogin(
        email: String,
        phoneNumber: String,
        password: String
    ) {
        viewModelScope.launch {
            val response = repository.userLogin(
                UserLogin(
                    email,
                    phoneNumber,
                    password
                )
            )

            TODO("live data for token")
//            if (response.isSuccessful) {
//                registered.postValue(response.body())
//            } else {
//                errorMessage.postValue(response.errorBody().toString())
//            }
        }
    }
}