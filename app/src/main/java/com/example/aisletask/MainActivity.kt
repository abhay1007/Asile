package com.example.aisletask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.aisletask.data.NotesResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    // Base URL


    // API service
    private lateinit var apiService: PhoneNumberApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create an instance of the API service
        apiService = ApiServiceBuilder.getApiService()

        // Prepare the authorization token
        val authToken = "32c7794d2e6a1f7316ef35aa1eb34541" // Replace with your actual auth token

        // Make the API call
        getNotes(authToken)
    }

    private fun getNotes(authToken: String) {
        // Make the API call
        val call = apiService.getNotesData(authToken)

        // Execute the call asynchronously
        call.enqueue(object : Callback<NotesResponse> {
            override fun onResponse(call: Call<NotesResponse>, response: Response<NotesResponse>) {
                if (response.isSuccessful) {
                    val notesResponse = response.body()
                    Log.d(TAG, "API call error: ${response.code()}")

                    // Handle the response data
                    // Access the profiles, likes, and other data through notesResponse object
                    notesResponse?.let { handleNotesResponse(it) }
                } else {
                    // Handle the error
                    val errorBody = response.errorBody()
                    Log.d(TAG, "API call error: ${response.code()}")

                    // Process the error body or show an error message
                    errorBody?.let { handleErrorResponse(it) }
                }
            }

            override fun onFailure(call: Call<NotesResponse>, t: Throwable) {
                // Handle the failure
                t.printStackTrace()
                // Show an error message or retry the request
                Log.d(TAG, "API call failed: ${t.message}")
            }
        })
    }

    private fun handleNotesResponse(notesResponse: NotesResponse) {
        // Process the notesResponse and update the UI accordingly
        // Example:
        val invites = notesResponse.invites
        val profiles = invites.profiles
        val totalPages = invites.totalPages
        val pendingInvitationsCount = invites.pendingInvitationsCount

        val likes = notesResponse.likes
        val likeProfiles = likes.profiles
        val canSeeProfile = likes.canSeeProfile
        val likesReceivedCount = likes.likesReceivedCount


    }

    private fun handleErrorResponse(errorBody: ResponseBody) {

        val errorMessage = errorBody.string()
        Log.d(TAG, "API call error: $errorMessage")
    }

    companion object {
        private const val TAG = "taggy"
    }
}
