package com.example.aisletask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aisletask.data.PhoneNumberRepository
import com.example.aisletask.data.VerifyResponse

class PhoneNumberLoginViewModel(private val phoneNumberRepository: PhoneNumberRepository) : ViewModel() {
    val loginResult = MutableLiveData<Boolean>()
    val otpResult = MutableLiveData<VerifyResponse>()




    fun loginUser(number: String) {
        phoneNumberRepository.loginUser(number) { result ->
            loginResult.postValue(result)
        }
    }

    fun verifyOTP(number: String, otp: String) {
        phoneNumberRepository.otpVerify(number, otp) { result, token ->
            if (result) {
                // Process the successful verification
                otpResult.postValue(token?.let { VerifyResponse(it) })
            } else {
                // Handle verification failure
                otpResult.postValue(null)
            }
        }
    }
//    fun fetchNotesData(authToken: String) {
//        _notesResult.value = Result.Loading()
//
//        phoneNumberRepository.getNotesData(authToken) { result ->
//            if (result.isSuccess) {
//                val apiResponse: ApiResponse? = result.getOrNull()
//                _notesResult.postValue(Result.Success(apiResponse))
//            } else {
//                val throwable: Throwable? = result.exceptionOrNull()
//                _notesResult.postValue(Result.Error(throwable))
//            }
//        }
//    }

    sealed class Result<out T> {
        data class Success<out T>(val data: T) : Result<T>()
        data class Error(val throwable: Throwable?) : Result<Nothing>()
        class Loading<out T> : Result<T>()
    }



}


