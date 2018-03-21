package com.oakkub.survey.data.repository.oauth

import com.oakkub.survey.data.response.OAuthResponse
import com.oakkub.survey.data.services.OAuthService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by oakkub on 22/3/2018 AD.
 */
class OAuthRepositoryImpl @Inject constructor(
        private val oAuthService: OAuthService
) : OAuthRepository {

    override fun authenticate(request: OAuthRequest): Observable<OAuthResponse> {
        return oAuthService.authenticate(request)
    }

}