package com.oakkub.survey.data.services.surveys

import com.oakkub.survey.common.constants.Endpoints
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by oakkub on 24/3/2018 AD.
 */
interface SurveysService {

    @GET(Endpoints.SURVEYS)
    fun getSurveys(@QueryMap request: SurveyRequest): Single<List<SurveyResponse>>

}