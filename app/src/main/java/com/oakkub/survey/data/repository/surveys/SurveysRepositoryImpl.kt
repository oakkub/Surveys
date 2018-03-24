package com.oakkub.survey.data.repository.surveys

import com.oakkub.survey.data.services.surveys.SurveyRequest
import com.oakkub.survey.data.services.surveys.SurveyResponse
import com.oakkub.survey.data.services.surveys.SurveysService
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by oakkub on 24/3/2018 AD.
 */
class SurveysRepositoryImpl @Inject constructor(private val surveysService: SurveysService) : SurveysRepository {

    override fun getSurveys(request: SurveyRequest): Single<List<SurveyResponse>> {
        return surveysService.getSurveys(request)
    }

}