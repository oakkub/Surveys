package com.oakkub.survey.data.repository.oauth

import com.oakkub.survey.data.response.OAuthResponse
import com.oakkub.survey.data.services.OAuthService
import com.oakkub.survey.exceptions.SurveysUnauthorizedException
import io.reactivex.Single
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Created by oakkub on 22/3/2018 AD.
 */
class OAuthRepositoryImpl @Inject constructor(
        private val oAuthService: OAuthService
) : OAuthRepository {

    override fun authenticate(request: OAuthRequest): Single<OAuthResponse> {
        return oAuthService.authenticate(request)
                .onErrorResumeNext {
                    if (it is HttpException && it.code() == 401) {
                        Single.error(SurveysUnauthorizedException())
                    } else {
                        Single.error(it)
                    }
                }
    }

}