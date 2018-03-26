package com.oakkub.survey.common.constants

/**
 * Created by oakkub on 21/3/2018 AD.
 */
object Endpoints {

    val BASE_URL: String
        get() = BaseUrlProviderImpl().baseUrl

    const val OAUTH = "oauth/token"
    const val SURVEYS = "surveys.json"

}