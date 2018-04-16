package com.oakkub.survey.common.constants

/**
 * Created by oakkub on 26/3/2018 AD.
 */
class BaseUrlProviderImpl : BaseUrlProvider {
    override val baseUrl: String
        get() = "https://nimbl3-survey-api.herokuapp.com/"
}
