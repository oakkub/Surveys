package com.oakkub.survey.data.services

import com.oakkub.survey.common.constants.Endpoints
import com.oakkub.survey.data.repository.oauth.OAuthRequest
import com.oakkub.survey.data.response.OAuthResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by oakkub on 22/3/2018 AD.
 */
interface OAuthService {

    @POST(Endpoints.OAUTH)
    fun authenticate(@Body request: OAuthRequest): Single<OAuthResponse>

}