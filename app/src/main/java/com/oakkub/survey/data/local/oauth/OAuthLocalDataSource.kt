package com.oakkub.survey.data.local.oauth

import com.oakkub.survey.data.response.OAuthResponse
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by oakkub on 23/3/2018 AD.
 */
interface OAuthLocalDataSource {

    fun save(oAuthResponse: OAuthResponse): Completable
    fun get(): Single<OAuthLocalResponse>

}