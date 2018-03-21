package com.oakkub.survey.data.repository.oauth

import com.oakkub.survey.data.response.OAuthResponse
import com.oakkub.survey.data.services.OAuthService
import com.oakkub.survey.exceptions.SurveysUnauthorizedException
import io.reactivex.Single
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
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
    private lateinit var oAuthService: OAuthService

    private lateinit var oAuthRepository: OAuthRepository

    @Before
    fun setup() {
        oAuthRepository = OAuthRepositoryImpl(oAuthService)
    }

    @Test
    fun `call authenticate should be ok`() {
        val request = OAuthRequest(
                grantType = "Duh",
                username = "Foo",
                password = "Bar"
        )
        val response = OAuthResponse(
                accessToken = "Yes",
                tokenType = "Test",
                expiresIn = 0,
                createdAt = 0
        )

        Mockito.`when`(oAuthService.authenticate(request))
                .thenReturn(Single.just(response))

        oAuthRepository.authenticate(request).test()
                .assertNoErrors()
                .assertValue(response)

        Mockito.verify(oAuthService, Mockito.times(1)).authenticate(request)

    }

    @Test
    fun `call authenticate with wrong oauth info should failed`() {
        val request = OAuthRequest(
                grantType = "Wrong",
                username = "Wrong",
                password = "All Wrong"
        )

        val errorResponseBody = ResponseBody.create(null, "")
        val errorResponse = Response.error<OAuthResponse>(401, errorResponseBody)

        Mockito.`when`(oAuthService.authenticate(request))
                .thenReturn(Single.error(HttpException(errorResponse)))

        oAuthRepository.authenticate(request).test()
                .assertError(SurveysUnauthorizedException::class.java)

        Mockito.verify(oAuthService, Mockito.times(1)).authenticate(request)
    }

}