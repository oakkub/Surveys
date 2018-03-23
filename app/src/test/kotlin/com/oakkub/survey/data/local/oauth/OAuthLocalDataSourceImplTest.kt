package com.oakkub.survey.data.local.oauth

import android.preference.PreferenceManager
import com.oakkub.survey.data.response.OAuthResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created by oakkub on 23/3/2018 AD.
 */
@Suppress("IllegalIdentifier")
@RunWith(RobolectricTestRunner::class)
class OAuthLocalDataSourceImplTest {

    lateinit var oAuthLocalDataSource: OAuthLocalDataSource

    @Before
    fun setUp() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(RuntimeEnvironment.application)
        oAuthLocalDataSource = OAuthLocalDataSourceImpl(prefs)
    }

    @Test
    fun `should save oAuthResponse into local storage correctly`() {
        val response = OAuthResponse("123456789", "ok", 1000L, 2000L)
        oAuthLocalDataSource.save(response)

        assertEquals(oAuthLocalDataSource.get(), response)
    }

    @Test
    fun `get oAuthResponse from local storage without saving it should be null`() {
        assertEquals(oAuthLocalDataSource.get(), null)
    }

}