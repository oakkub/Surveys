package com.oakkub.survey.data.repository.oauth

import com.oakkub.survey.data.local.oauth.OAuthLocalDataSource
import com.oakkub.survey.data.local.oauth.OAuthLocalResponse
import com.oakkub.survey.data.services.oauth.OAuthRequest
import com.oakkub.survey.data.services.oauth.OAuthResponse
import com.oakkub.survey.data.services.oauth.OAuthService
import com.oakkub.survey.exceptions.SurveysUnauthorizedException
import com.oakkub.survey.ext.then
import com.oakkub.survey.ext.whenever
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.ResponseBody
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response

/**
 * Created by oakkub on 22/3/2018 AD.
 */
@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class OAuthRepositoryImplTest {

    @Mock
    lateinit var oAuthService: OAuthService

    @Mock
    lateinit var oAuthLocalDataSource: OAuthLocalDataSource

    @Test
    fun `call authenticate should get from local`() {
        val request = OAuthRequest(grantType = "Duh", username = "Foo", password = "Bar")
        val response = OAuthResponse(accessToken = "Yes", tokenType = "Test", expiresIn = 1000L, createdAt = 2500L)

        val oAuthRepository = OAuthRepositoryImpl(oAuthService, oAuthLocalDataSource)

        whenever(oAuthLocalDataSource.get())
                .thenReturn(Single.just(OAuthLocalResponse.Success(response)))

        oAuthRepository.authenticate(request).test()
                .assertValue(response)

        verify(oAuthLocalDataSource).get()
    }

    @Test
    fun `call authenticate should get from network`() {
        val request = OAuthRequest(grantType = "Duh", username = "Foo", password = "Bar")
        val response = OAuthResponse(accessToken = "Yes", tokenType = "Test", expiresIn = 1000L, createdAt = 2500L)

        val oAuthRepository = OAuthRepositoryImpl(oAuthService, oAuthLocalDataSource)

        whenever(oAuthLocalDataSource.get()) then {
            Single.just(OAuthLocalResponse.Expired)
        }

        whenever(oAuthService.authenticate(request)) then {
            Single.just(response)
        }

        whenever(oAuthLocalDataSource.save(response)) then {
            Completable.complete()
        }

        oAuthRepository.authenticate(request)
                .test()
                .assertValue(response)

        verify(oAuthLocalDataSource).get()
        verify(oAuthService).authenticate(request)
    }

    @Test
    fun `call authenticate from network with wrong oauth info should failed with SurveyUnauthorizedException`() {
        val request = OAuthRequest(grantType = "Wrong", username = "Wrong", password = "All Wrong")
        val response = OAuthResponse(accessToken = "Yes", tokenType = "Test", expiresIn = 1000L, createdAt = 2500L)

        val oAuthRepository = OAuthRepositoryImpl(oAuthService, oAuthLocalDataSource)

        whenever(oAuthLocalDataSource.get()) then {
            Single.just(OAuthLocalResponse.Empty)
        }

        whenever(oAuthService.authenticate(request)) then {
            val errorResponseBody = ResponseBody.create(null, "")
            val errorResponse = Response.error<OAuthResponse>(401, errorResponseBody)
            Single.error(HttpException(errorResponse))
        }

        oAuthRepository.authenticate(request).test()
                .assertError(SurveysUnauthorizedException::class.java)

        verify(oAuthLocalDataSource).get()
        verify(oAuthService).authenticate(request)
        verify(oAuthLocalDataSource, times(0)).save(response)
    }

}
