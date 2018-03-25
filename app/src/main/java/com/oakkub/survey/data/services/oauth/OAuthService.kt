package com.oakkub.survey.data.services.oauth

import com.oakkub.survey.common.constants.Endpoints
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