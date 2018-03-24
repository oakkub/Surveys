package com.oakkub.survey.data.repository.oauth

import com.oakkub.survey.data.services.oauth.OAuthRequest
import com.oakkub.survey.data.services.oauth.OAuthResponse
import io.reactivex.Single

/**
 * Created by oakkub on 22/3/2018 AD.
 */
interface OAuthRepository {

    fun authenticate(request: OAuthRequest): Single<OAuthResponse>

}