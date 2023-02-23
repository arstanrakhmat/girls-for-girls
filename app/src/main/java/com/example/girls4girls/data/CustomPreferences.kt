package com.example.girls4girls.data

import android.content.Context
import android.content.SharedPreferences
import com.example.girls4girls.utils.Constants

class CustomPreferences(context: Context) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences("App", Context.MODE_PRIVATE)
    private val editor = preferences.edit()

    fun saveToken(token: String?) {
        if (token != null) {
            editor.putString(Constants.TOKEN, token).apply()
        }
    }

    fun fetchToken(): String? {
        return preferences.getString(Constants.TOKEN, "token")
    }
}