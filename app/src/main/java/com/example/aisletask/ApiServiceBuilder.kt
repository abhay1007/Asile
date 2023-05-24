package com.example.aisletask

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceBuilder {
    private const val BASE_URL = "https://app.aisle.co/V1/"

    fun getApiService(): PhoneNumberApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(PhoneNumberApiService::class.java)
    }
}
