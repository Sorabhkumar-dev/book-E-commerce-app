package com.sorabh.readerclub.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GetBookAPI {
    val BASE_URL = "http://13.235.250.119/v1/book/"
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}