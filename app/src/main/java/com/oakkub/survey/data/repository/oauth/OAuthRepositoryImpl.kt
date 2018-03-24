package com.oakkub.survey.data.repository.oauth

import com.oakkub.survey.data.local.oauth.OAuthLocalDataSource
import com.oakkub.survey.data.local.oauth.OAuthLocalResponse
import com.oakkub.survey.data.services.oauth.OAuthResponse
import com.oakkub.survey.data.services.oauth.OAuthService
import com.oakkub.survey.exceptions.SurveysUnauthorizedException
import io.reactivex.Single
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Created by oakkub on 22/3/2018 AD.
 */
class OAuthRepositoryImpl @Inject constructor(
        private val oAuthService: OAuthService,
        private val oAuthLocalDataSource: OAuthLocalDataSource
) : OAuthRepository {

    override fun authenticate(request: OAuthRequest): Single<OAuthResponse> {
        val localOAuthSingle = oAuthLocalDataSource.get()
        val remoteOAuthSingle = Single
                .defer { oAuthService.authenticate(request) }
                .flatMap { oAuthResponse ->
                    oAuthLocalDataSource.save(oAuthResponse).toSingle { oAuthResponse }
                }
        return localOAuthSingle
                .flatMap {
                    when (it) {
                        is OAuthLocalResponse.Expired,
                        is OAuthLocalResponse.Empty -> remoteOAuthSingle
                        is OAuthLocalResponse.Success -> Single.just(it.response)
                    }
                }
                .onErrorResumeNext { throwable ->
                    if (throwable is HttpException && throwable.code() == 401) {
                        Single.error(SurveysUnauthorizedException())
                    } else {
                        Single.error(throwable)
                    }
                }
    }

}