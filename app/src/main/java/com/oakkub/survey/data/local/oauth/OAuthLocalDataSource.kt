package com.oakkub.survey.data.local.oauth

import com.oakkub.survey.data.response.OAuthResponse

/**
 * Created by oakkub on 23/3/2018 AD.
 */
interface OAuthLocalDataSource {

    fun save(oAuthResponse: OAuthResponse)
    fun get(): OAuthResponse?

}