package com.example.aisletask.data
import com.google.gson.annotations.SerializedName

data class NotesResponse(
    @SerializedName("invites")
    val invites: Invites,
    @SerializedName("likes")
    val likes: Likes
) {
    data class Invites(
        @SerializedName("profiles")
        val profiles: List<Profile>,
        @SerializedName("totalPages")
        val totalPages: Int,
        @SerializedName("pending_invitations_count")
        val pendingInvitationsCount: Int
    )

    data class Likes(
        @SerializedName("profiles")
        val profiles: List<Profile>,
        @SerializedName("can_see_profile")
        val canSeeProfile: Boolean,
        @SerializedName("likes_received_count")
        val likesReceivedCount: Int
    )

    data class Profile(
        @SerializedName("general_information")
        val generalInformation: GeneralInformation,
        @SerializedName("approved_time")
        val approvedTime: Double,
        @SerializedName("disapproved_time")
        val disapprovedTime: Double,
        @SerializedName("photos")
        val photos: List<Photo>,
        @SerializedName("user_interests")
        val userInterests: List<Any>,
        @SerializedName("work")
        val work: Work,
        @SerializedName("preferences")
        val preferences: List<Preference>,
        // Add other properties as needed
    )

    data class GeneralInformation(
        @SerializedName("date_of_birth")
        val dateOfBirth: String,
        @SerializedName("date_of_birth_v1")
        val dateOfBirthV1: String,
        @SerializedName("location")
        val location: Location,
        // Add other properties as needed
    )

    data class Location(
        @SerializedName("summary")
        val summary: String,
        @SerializedName("full")
        val full: String
    )

    data class Photo(
        @SerializedName("photo")
        val photo: String,
        @SerializedName("photo_id")
        val photoId: Int,
        @SerializedName("selected")
        val selected: Boolean,
        @SerializedName("status")
        val status: String?
    )

    data class Work(
        @SerializedName("industry_v1")
        val industry: Industry,
        @SerializedName("monthly_income_v1")
        val monthlyIncome: Any?,
        @SerializedName("experience_v1")
        val experience: Experience,
        @SerializedName("highest_qualification_v1")
        val highestQualification: HighestQualification,
        @SerializedName("field_of_study_v1")
        val fieldOfStudy: FieldOfStudy
    )

    data class Industry(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("preference_only")
        val preferenceOnly: Boolean
    )

    data class Experience(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("name_alias")
        val nameAlias: String
    )

    data class HighestQualification(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("preference_only")
        val preferenceOnly: Boolean
    )

    data class FieldOfStudy(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )

    data class Preference(
        @SerializedName("answer_id")
        val answerId: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("value")
        val value: Int,
        @SerializedName("preference_question")
        val preferenceQuestion: PreferenceQuestion
    )

    data class PreferenceQuestion(
        @SerializedName("first_choice")
        val firstChoice: String,
        @SerializedName("second_choice")
        val secondChoice: String
    )

    // Add other nested data classes as needed
}
