package com.oakkub.survey.common.interceptors

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by oakkub on 22/3/2018 AD.
 */
class QueryParamsInjectInterceptor(private val specificsPath: List<String> = emptyList(),
                                   private val requiredQueryParameters: List<QueryParameters>) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val didNotFindSpecifyPath = specificsPath.find { it == request.url().pathSegments().joinToString("/") } == null
        val didNotSpecifyPath = !specificsPath.isEmpty()
        if (didNotFindSpecifyPath && didNotSpecifyPath) {
            return chain.proceed(request)
        }

        val newUrl = request.url().newBuilder().apply {
            requiredQueryParameters.forEach {
                val (name, value) = it
                addEncodedQueryParameter(name, value)
            }
        }.build()

        val newRequest = request.newBuilder()
                .url(newUrl)
                .build()
        return chain.proceed(newRequest)
    }

}

typealias QueryParameters = Pair<String, String>
