package com.example.girls4girls.koin.application

import android.app.Application
import com.example.girls4girls.koin.retrofitModule
import com.example.girls4girls.koin.viewModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(retrofitModule, viewModules))
            androidContext(this@MyApplication)
        }
    }
}