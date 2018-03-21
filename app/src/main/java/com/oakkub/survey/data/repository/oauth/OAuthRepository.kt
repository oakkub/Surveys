package com.oakkub.survey.data.repository.oauth

import com.oakkub.survey.data.response.OAuthResponse
import io.reactivex.Observable

/**
 * Created by oakkub on 22/3/2018 AD.
 */
interface OAuthRepository {

    fun authenticate(request: OAuthRequest): Observable<OAuthResponse>

}