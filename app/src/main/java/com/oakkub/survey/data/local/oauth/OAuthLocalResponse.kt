package com.oakkub.survey.data.local.oauth

import com.oakkub.survey.data.response.OAuthResponse

/**
 * Created by oakkub on 24/3/2018 AD.
 */
sealed class OAuthLocalResponse {
    data class Success(val response: OAuthResponse) : OAuthLocalResponse()
    object Expired : OAuthLocalResponse()
    object Empty : OAuthLocalResponse()
}