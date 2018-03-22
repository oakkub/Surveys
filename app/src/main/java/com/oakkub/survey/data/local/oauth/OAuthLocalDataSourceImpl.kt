package com.oakkub.survey.data.local.oauth

import com.oakkub.survey.data.response.OAuthResponse

/**
 * Created by oakkub on 23/3/2018 AD.
 */
class OAuthLocalDataSourceImpl : OAuthLocalDataSource {

    override fun save(oAuthResponse: OAuthResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(): OAuthResponse? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

private const val PREF_KEY_O_AUTH_REPO_ACCESS_TOKEN = "PREF_KEY_O_AUTH_REPO_ACCESS_TOKEN"
private const val PREF_KEY_O_AUTH_REPO_EXPIRE_IN = "PREF_KEY_O_AUTH_REPO_EXPIRE_IN"
private const val PREF_KEY_O_AUTH_REPO_CREATED_AT = "PREF_KEY_O_AUTH_REPO_CREATED_AT"