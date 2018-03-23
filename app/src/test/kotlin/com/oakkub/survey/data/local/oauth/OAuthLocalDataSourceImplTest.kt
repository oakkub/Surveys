package com.oakkub.survey.data.local.oauth

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.oakkub.survey.common.date.TimestampGetterImplForTest
import com.oakkub.survey.data.response.OAuthResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Created by oakkub on 23/3/2018 AD.
 */
@Suppress("IllegalIdentifier")
@RunWith(RobolectricTestRunner::class)
@Config(manifest=Config.NONE)
class OAuthLocalDataSourceImplTest {

    private lateinit var prefs: SharedPreferences

    @Before
    fun setUp() {
        prefs = PreferenceManager.getDefaultSharedPreferences(RuntimeEnvironment.application)
    }

    @Test
    fun `should save oAuthResponse into local storage and retrieve it correctly`() {
        val oAuthLocalDataSource = OAuthLocalDataSourceImpl(prefs, TimestampGetterImplForTest(3000L))
        val response = OAuthResponse("123456789", "ok", 1000L, 2500L)
        oAuthLocalDataSource.save(response)

        assertEquals(oAuthLocalDataSource.get(), response)
    }

    @Test
    fun `get oAuthResponse from local storage without saving it should be null`() {
        val oAuthLocalDataSource = OAuthLocalDataSourceImpl(prefs, TimestampGetterImplForTest(3000L))
        assertEquals(oAuthLocalDataSource.get(), null)
    }

    @Test
    fun `get oAuthResponse from local storage with stale timestamp should be null`() {
        val timestampGetter = TimestampGetterImplForTest(3000L)
        val oAuthLocalDataSource = OAuthLocalDataSourceImpl(prefs, timestampGetter)
        val response = OAuthResponse("123456789", "foo", 1000L, 1000L)
        oAuthLocalDataSource.save(response)

        assertNull(oAuthLocalDataSource.get())
    }

}