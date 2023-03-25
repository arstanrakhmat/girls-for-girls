package com.example.girls4girls.presentation.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.girls4girls.data.model.User
import com.example.girls4girls.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    val user = MutableLiveData<User>()
    val errorMessage = MutableLiveData<String>()

    fun getUser(string: String?) {
        viewModelScope.launch {
            val response = repository.getUser(string)
            if (response.isSuccessful) {
                user.postValue(response.body())
            } else {
                errorMessage.postValue(response.errorBody().toString())
            }
        }
    }
}