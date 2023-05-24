package com.example.aisletask.data

import android.util.Log
import com.example.aisletask.LoginRequest
import com.example.aisletask.LoginResponse
import com.example.aisletask.PhoneNumberApiService
import com.example.aisletask.VerifyRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhoneNumberRepository(private val apiService: PhoneNumberApiService) {
    fun loginUser(number: String, onResult: (Boolean) -> Unit) {
        val request = LoginRequest(number)
        apiService.loginUser(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val phoneNumberLoginResponse = response.body()
                    if (phoneNumberLoginResponse?.status == true) {
                        onResult(true)
                    } else {
                        onResult(false)
                    }
                } else {
                    onResult(false)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onResult(false)
            }
        })
    }

    fun otpVerify(number: String, otp: String, onResult: (Boolean, String?) -> Unit) {
        val request = VerifyRequest(number, otp)
        apiService.verifyUser(request).enqueue(object : Callback<VerifyResponse> {
            override fun onResponse(call: Call<VerifyResponse>, response: Response<VerifyResponse>) {
                if (response.isSuccessful) {
                    val otpVerifyResponse = response.body()
                    if (otpVerifyResponse != null) {
                        val token = otpVerifyResponse.token
                        onResult(true, token)
                    } else {
                        // Handle null response body
                        onResult(false, null)
                    }
                } else {
                    // Handle API call failure
                    onResult(false, null)
                }
            }

            override fun onFailure(call: Call<VerifyResponse>, t: Throwable) {
                // Handle API call failure
                onResult(false, null)
            }
        })
    }
//    fun getNotesData(authToken: String, callback: (Result<ApiResponse>) -> Unit) {
//        val call = apiService.getNotesData(authToken)
//        call.enqueue(object : Callback<ApiResponse> {
//            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
//                if (response.isSuccessful) {
//                    val notesResponse = response.body()
//                    Log.d("taggy",notesResponse.toString())
//                    if (notesResponse != null) {
//                        callback(Result.success(notesResponse))
//                    } else {
//                        callback(Result.failure(Exception("Empty response body")))
//                        Log.d("taggy",notesResponse.toString())
//                    }
//                } else {
//                    callback(Result.failure(Exception("API call failed with code ${response.code()}")))
//                    Log.d("taggy",response.message().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
//                callback(Result.failure(t))
//            }
//        })
//    }

}
