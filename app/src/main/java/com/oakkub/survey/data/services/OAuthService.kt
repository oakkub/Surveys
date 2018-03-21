package com.oakkub.survey.data.services

import com.oakkub.survey.constants.Endpoints
import io.reactivex.Observable
import retrofit2.http.POST

/**
 * Created by oakkub on 22/3/2018 AD.
 */
interface OAuthService {

    @POST(Endpoints.OAUTH)
    fun authenticate(): Observable<OAuthService>

}