package com.example.aisletask

import com.example.aisletask.data.NotesResponse
import com.example.aisletask.data.VerifyResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface PhoneNumberApiService {
    @POST("users/phone_number_login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>
    @POST("users/verify_otp")
    fun verifyUser(@Body request: VerifyRequest): Call<VerifyResponse>
    @GET("/users/test_profile_list")
    fun getNotesData(@Header("Authorization") authtoken: String): Call<NotesResponse>


}
