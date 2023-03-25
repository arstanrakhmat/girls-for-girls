package com.example.girls4girls.presentation.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.girls4girls.data.model.*
import com.example.girls4girls.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    val registered = MutableLiveData<UserRegistrationResponse>()
    val activated = MutableLiveData<UserActivateResponse>()
    val errorMessage = MutableLiveData<String>()
    val token = MutableLiveData<UserLoginResponse>()

    val resendOtp = MutableLiveData<UserRegistrationResponse>()

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

    fun resendOtp(
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
                resendOtp.postValue(response.body())
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
                activated.postValue(response.body())
            } else {
                errorMessage.postValue(response.errorBody().toString())
            }
        }
    }

    fun userLoginEmail(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            val response = repository.userLoginEmail(
                UserLoginEmail(
                    email,
                    password
                )
            )

            if (response.isSuccessful) {
                token.postValue(response.body())
            } else {
                errorMessage.postValue(response.errorBody().toString())
            }
        }
    }

    fun userLoginPhoneNumber(
        phoneNumber: String,
        password: String
    ) {
        viewModelScope.launch {
            val response = repository.userLoginPhoneNumber(
                UserLoginPhoneNumber(
                    phoneNumber,
                    password
                )
            )

            if (response.isSuccessful) {
                token.postValue(response.body())
            } else {
                errorMessage.postValue(response.errorBody().toString())
            }
        }
    }
}