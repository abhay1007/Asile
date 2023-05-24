package com.example.aisletask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.aisletask.data.PhoneNumberRepository
import com.example.aisletask.data.VerifyResponse
import com.example.aisletask.ui.PhoneNumberLoginViewModel

class OtpVerifyActivity : AppCompatActivity() {
    private lateinit var viewModel: PhoneNumberLoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verify)

        // Initialize views
        val otpEditText: EditText = findViewById(R.id.Otp_ET)
        val continueButton: Button = findViewById(R.id.Continue_Btn_otp)
        val timerTextView: TextView = findViewById(R.id.some_id)

        // Create an instance of PhoneNumberRepository
        val phoneNumberRepository = PhoneNumberRepository(ApiServiceBuilder.getApiService())
        viewModel = ViewModelProvider(this, PhoneNumberLoginViewModelFactory(phoneNumberRepository))
            .get(PhoneNumberLoginViewModel::class.java)

        // Set the observer for the otpResult LiveData
        viewModel.otpResult.observe(this, { response: VerifyResponse? ->
            // Handle the OTP verification response here
            if (response != null) {
                // Verification successful
                // You can access the token from the response if needed: response.token
                Toast.makeText(applicationContext, "API call successful", Toast.LENGTH_LONG).show()
                Log.d("taggy",response.token)
                navigateToMainActivity(response.token)

            } else {
                // Verification failed
                Toast.makeText(applicationContext, "API call unsuccessful", Toast.LENGTH_LONG).show()

            }
        })

        // Set click listener for the Continue button
        continueButton.setOnClickListener {
            val otp = otpEditText.text.toString().trim()
            val phoneNumber = "+919876543212" // Change to your desired phone number

            // Call the verifyOTP function in the ViewModel to verify the OTP
            viewModel.verifyOTP(phoneNumber, otp)
        }

    }
    private fun navigateToMainActivity(authToken: String) {
        // Create an Intent to start the MainActivity
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("auth_token", authToken)

        // Start the MainActivity
        startActivity(intent)

        // Finish the current activity (OTPVerificationActivity)
        finish()
    }
}

