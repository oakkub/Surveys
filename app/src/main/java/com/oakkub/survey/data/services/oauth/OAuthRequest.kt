package com.oakkub.survey.data.services.oauth

import com.google.gson.annotations.SerializedName

/**
 * Created by oakkub on 22/3/2018 AD.
 */
data class OAuthRequest(
        @SerializedName("grant_type") val grantType: String,
        @SerializedName("username") val username: String,
        @SerializedName("password") val password: String
)
