package com.oakkub.survey.data.repository.surveys

import com.oakkub.survey.data.services.surveys.SurveyRequest
import com.oakkub.survey.data.services.surveys.SurveyResponse
import io.reactivex.Single

/**
 * Created by oakkub on 24/3/2018 AD.
 */
interface SurveysRepository {

    fun getSurveys(request: SurveyRequest): Single<List<SurveyResponse>>

}