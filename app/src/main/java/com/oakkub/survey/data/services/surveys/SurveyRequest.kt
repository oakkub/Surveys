package com.oakkub.survey.data.services.surveys

/**
 * Created by oakkub on 24/3/2018 AD.
 */
class SurveyRequest(page: Int, perPage: Int, accessToken: String) : HashMap<String, String>() {

    init {
        put("page", page.toString())
        put("per_page", perPage.toString())
        put("access_token", accessToken)
    }

}
