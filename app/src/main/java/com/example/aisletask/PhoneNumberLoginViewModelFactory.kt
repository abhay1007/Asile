package com.example.aisletask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aisletask.data.PhoneNumberRepository
import com.example.aisletask.ui.PhoneNumberLoginViewModel

class PhoneNumberLoginViewModelFactory(private val phoneNumberRepository: PhoneNumberRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhoneNumberLoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PhoneNumberLoginViewModel(phoneNumberRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
