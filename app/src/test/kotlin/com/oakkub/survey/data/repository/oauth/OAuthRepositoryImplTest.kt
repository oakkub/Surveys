package com.oakkub.survey.data.repository.oauth

import com.oakkub.survey.data.services.OAuthService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by oakkub on 22/3/2018 AD.
 */
@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class OAuthRepositoryImplTest {

    @Mock
    lateinit var oAuthService: OAuthService

    lateinit var oAuthRepository: OAuthRepository

    @Before
    fun setup() {
        oAuthRepository = OAuthRepositoryImpl(oAuthService)
    }

    @Test
    fun `call authenticate should be ok`() {
        
    }

}