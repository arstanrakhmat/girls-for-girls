package com.example.girls4girls.koin

import com.example.girls4girls.data.CustomPreferences
import com.example.girls4girls.data.api.Api
import com.example.girls4girls.data.repository.AuthRepository
import com.example.girls4girls.data.repository.ResetPasswordRepository
import com.example.girls4girls.data.repository.TrainingRepository
import com.example.girls4girls.data.repository.UserRepository
import com.example.girls4girls.presentation.account.UserViewModel
import com.example.girls4girls.presentation.auth.AuthViewModel
import com.example.girls4girls.presentation.auth.ResetPasswordViewModel
import com.example.girls4girls.presentation.training.TrainingViewModel
import com.example.girls4girls.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    single { getOkHttpInstance() }
    single { getRetrofitInstance(get()) }
    single { getApiInstance(get()) }
    single { CustomPreferences(androidApplication()) }
    factory { AuthRepository(api = get()) }
    factory { UserRepository(api = get()) }
    factory { TrainingRepository(api = get()) }
    factory { ResetPasswordRepository(api = get()) }
}

val viewModules = module {
    viewModel { AuthViewModel(repository = get()) }
    viewModel { UserViewModel(repository = get()) }
    viewModel { ResetPasswordViewModel(repository = get()) }
    viewModel { TrainingViewModel(repository = get()) }
}

fun getApiInstance(retrofit: Retrofit): Api {
    return retrofit.create(Api::class.java)
}

fun getRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun getOkHttpInstance(): OkHttpClient {
    return OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(5, TimeUnit.MINUTES)
        .readTimeout(5, TimeUnit.MINUTES)
        .build()
}