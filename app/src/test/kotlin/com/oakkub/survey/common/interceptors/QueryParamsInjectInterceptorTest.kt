package com.oakkub.survey.common.interceptors

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by oakkub on 22/3/2018 AD.
 */
@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class QueryParamsInjectInterceptorTest {

    private val mockWebServer: MockWebServer = MockWebServer()

    @Before
    fun setup() {
        mockWebServer.start()
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `url should have query access_token=123456789`() {
        mockWebServer.enqueue(MockResponse())

        OkHttpClient.Builder()
                .addInterceptor(QueryParamsInjectInterceptor(
                        specificsPath = emptyList(),
                        requiredQueryParameters = listOf("access_token" to "123456789"))
                )
                .build()
                .newCall(createRequest())
                .execute()

        val recordedRequest = mockWebServer.takeRequest()
        assertEquals(recordedRequest.requestUrl.query(), "access_token=123456789")
    }

    @Test
    fun `url should have multiple query appended`() {
        mockWebServer.enqueue(MockResponse())

        OkHttpClient.Builder()
                .addInterceptor(QueryParamsInjectInterceptor(
                        specificsPath = emptyList(),
                        requiredQueryParameters = listOf(
                                "grant_type" to "ok",
                                "username" to "metas",
                                "password" to "kerdwat"
                        ))
                )
                .build()
                .newCall(createRequest())
                .execute()

        val recordedRequest = mockWebServer.takeRequest()
        val expectedQuery = "grant_type=ok&username=metas&password=kerdwat"
        assertTrue(recordedRequest.requestUrl.query()?.contains(expectedQuery) ?: false)
    }

    @Test
    fun `url should not have query access_token=123456789 unless the specified path is 'yolo'`() {
        fun executeNetworkClientWithDesiredPath(path: String) {
            mockWebServer.enqueue(MockResponse())

            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(QueryParamsInjectInterceptor(
                            specificsPath = listOf("yolo"),
                            requiredQueryParameters = listOf("access_token" to "123456789"))
                    )
                    .build()
                    .newCall(createRequest(path))
            okHttpClient.execute()
        }

        executeNetworkClientWithDesiredPath("yolo")
        val recordedFirstRequest = mockWebServer.takeRequest()
        assertEquals(recordedFirstRequest.requestUrl.query(), "access_token=123456789")

        executeNetworkClientWithDesiredPath("notyolo")
        val recordedSecondRequest = mockWebServer.takeRequest()
        assertEquals(recordedSecondRequest.requestUrl.query(), null)
    }

    private fun createRequest(path: String = "/"): Request = Request.Builder()
            .url(mockWebServer.url(path))
            .build()

}
