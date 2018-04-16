package com.oakkub.survey.data.local.oauth

import android.content.SharedPreferences
import androidx.content.edit
import com.oakkub.survey.common.date.TimestampGetter
import com.oakkub.survey.data.services.oauth.OAuthResponse
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by oakkub on 23/3/2018 AD.
 */
class OAuthLocalDataSourceImpl @Inject constructor(
        private val sharedPreferences: SharedPreferences,
        private val timestampGetter: TimestampGetter
) : OAuthLocalDataSource {

    override fun save(oAuthResponse: OAuthResponse): Completable = Completable.fromCallable {
        sharedPreferences.edit(commit = true) {
            putString(PREF_KEY_O_AUTH_REPO_ACCESS_TOKEN, oAuthResponse.accessToken)
            putString(PREF_KEY_O_AUTH_REPO_TOKEN_TYPE, oAuthResponse.tokenType)
            putLong(PREF_KEY_O_AUTH_REPO_EXPIRES_IN, oAuthResponse.expiresIn)
            putLong(PREF_KEY_O_AUTH_REPO_CREATED_AT, oAuthResponse.createdAt)
        }
    }

    override fun get(): Single<OAuthLocalResponse> = Single.create { emitter ->
        val accessToken = sharedPreferences.getString(PREF_KEY_O_AUTH_REPO_ACCESS_TOKEN, "")
        val tokenType = sharedPreferences.getString(PREF_KEY_O_AUTH_REPO_TOKEN_TYPE, "")
        val expiresIn = sharedPreferences.getLong(PREF_KEY_O_AUTH_REPO_EXPIRES_IN, -1)
        val createdAt = sharedPreferences.getLong(PREF_KEY_O_AUTH_REPO_CREATED_AT, -1)

        if (isLocalDataSourceNotExist(sharedPreferences)) {
            emitter.onSuccess(OAuthLocalResponse.Empty)
            return@create
        }

        val isExpired = timestampGetter.getCurrentTimestamp() - createdAt > expiresIn
        if (isExpired) {
            remove(sharedPreferences)
            emitter.onSuccess(OAuthLocalResponse.Expired)
            return@create
        }

        val oAuthResponse = OAuthResponse(accessToken, tokenType, expiresIn, createdAt)
        emitter.onSuccess(OAuthLocalResponse.Success(oAuthResponse))
    }

    private fun remove(sharedPreferences: SharedPreferences) {
        sharedPreferences.edit(commit = true) {
            remove(PREF_KEY_O_AUTH_REPO_ACCESS_TOKEN)
            remove(PREF_KEY_O_AUTH_REPO_TOKEN_TYPE)
            remove(PREF_KEY_O_AUTH_REPO_EXPIRES_IN)
            remove(PREF_KEY_O_AUTH_REPO_CREATED_AT)
        }
    }

    private fun isLocalDataSourceNotExist(sharedPreferences: SharedPreferences): Boolean {
        return sharedPreferences.getString(PREF_KEY_O_AUTH_REPO_ACCESS_TOKEN, "").isBlank()
    }

}

private const val PREF_KEY_O_AUTH_REPO_ACCESS_TOKEN = "PREF_KEY_O_AUTH_REPO_ACCESS_TOKEN"
private const val PREF_KEY_O_AUTH_REPO_TOKEN_TYPE = "PREF_KEY_O_AUTH_REPO_TOKEN_TYPE"
private const val PREF_KEY_O_AUTH_REPO_EXPIRES_IN = "PREF_KEY_O_AUTH_REPO_EXPIRES_IN"
private const val PREF_KEY_O_AUTH_REPO_CREATED_AT = "PREF_KEY_O_AUTH_REPO_CREATED_AT"