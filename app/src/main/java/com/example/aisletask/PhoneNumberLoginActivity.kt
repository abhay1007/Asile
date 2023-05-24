package com.example.aisletask
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aisletask.data.PhoneNumberRepository
import com.example.aisletask.ui.PhoneNumberLoginViewModel

class PhoneNumberLoginActivity : AppCompatActivity() {
    private lateinit var viewModel: PhoneNumberLoginViewModel
    private lateinit var numberEt: EditText
    private lateinit var continueBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number_login_activity)

        val phoneNumberRepository = PhoneNumberRepository(ApiServiceBuilder.getApiService())
        viewModel = ViewModelProvider(this, PhoneNumberLoginViewModelFactory(phoneNumberRepository))
            .get(PhoneNumberLoginViewModel::class.java)

        viewModel.loginResult.observe(this, Observer { result ->
            if (result) {
                Toast.makeText(applicationContext, "API call successful", Toast.LENGTH_LONG).show()
                val intent = Intent(this, OtpVerifyActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "API call unsuccessful", Toast.LENGTH_LONG).show()
            }
        })

        numberEt = findViewById(R.id.numberEt)
        continueBtn = findViewById(R.id.Continue_Btn)
        continueBtn.setOnClickListener {
            val number = "+91"+numberEt.text.toString()
            if (number.isEmpty()) {
                numberEt.error = "Please enter a phone number"
            } else {
                viewModel.loginUser(number)
            }
        }
    }
}
