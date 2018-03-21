package com.oakkub.survey.data.response

import com.google.gson.annotations.SerializedName


/**
 * Created by oakkub on 22/3/2018 AD.
 */
data class OAuthResponse(
        @SerializedName("access_token") val accessToken: String,
        @SerializedName("token_type") val tokenType: String,
        @SerializedName("expires_in") val expiresIn: Long,
        @SerializedName("created_at") val createdAt: Long
)