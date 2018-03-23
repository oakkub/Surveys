package com.oakkub.survey.data.local.oauth

import android.content.SharedPreferences
import androidx.content.edit
import com.oakkub.survey.common.date.TimestampGetter
import com.oakkub.survey.data.response.OAuthResponse
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by oakkub on 23/3/2018 AD.
 */
class OAuthLocalDataSourceImpl @Inject constructor(private val sharedPreferences: SharedPreferences,
                                                   private val timestampGetter: TimestampGetter) : OAuthLocalDataSource {

    override fun save(oAuthResponse: OAuthResponse): Completable = Completable.fromCallable {
        sharedPreferences.edit(commit = true) {
            putString(PREF_KEY_O_AUTH_REPO_ACCESS_TOKEN, oAuthResponse.accessToken)
            putString(PREF_KEY_O_AUTH_REPO_TOKEN_TYPE, oAuthResponse.tokenType)
            putLong(PREF_KEY_O_AUTH_REPO_EXPIRES_IN, oAuthResponse.expiresIn)
            putLong(PREF_KEY_O_AUTH_REPO_CREATED_AT, oAuthResponse.createdAt)
        }
    }

    override fun get(): Single<OAuthResponse> = Single.create { emitter ->
        val accessToken = sharedPreferences.getString(PREF_KEY_O_AUTH_REPO_ACCESS_TOKEN, "")
        val tokenType = sharedPreferences.getString(PREF_KEY_O_AUTH_REPO_TOKEN_TYPE, "")
        val expiresIn = sharedPreferences.getLong(PREF_KEY_O_AUTH_REPO_EXPIRES_IN, -1)
        val createdAt = sharedPreferences.getLong(PREF_KEY_O_AUTH_REPO_CREATED_AT, -1)

        if (accessToken == "" && tokenType == "" && expiresIn == -1L && createdAt == -1L) {
            emitter.onError(NullPointerException())
        }

        val isExpired = timestampGetter.getCurrentTimestamp() - createdAt > expiresIn
        if (isExpired) {
            emitter.onError(NullPointerException())
        }

        emitter.onSuccess(OAuthResponse(accessToken, tokenType, expiresIn, createdAt))
    }

}

private const val PREF_KEY_O_AUTH_REPO_ACCESS_TOKEN = "PREF_KEY_O_AUTH_REPO_ACCESS_TOKEN"
private const val PREF_KEY_O_AUTH_REPO_TOKEN_TYPE = "PREF_KEY_O_AUTH_REPO_TOKEN_TYPE"
private const val PREF_KEY_O_AUTH_REPO_EXPIRES_IN = "PREF_KEY_O_AUTH_REPO_EXPIRES_IN"
private const val PREF_KEY_O_AUTH_REPO_CREATED_AT = "PREF_KEY_O_AUTH_REPO_CREATED_AT"