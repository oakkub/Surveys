package com.oakkub.survey.ui.surveys.list.adapter.content

import com.oakkub.survey.data.services.surveys.SurveyResponse

/**
 * Created by oakkub on 25/3/2018 AD.
 */
sealed class SurveysItemAdapterModel {
    object Loading : SurveysItemAdapterModel()
    data class Item(val surveyResponse: SurveyResponse) : SurveysItemAdapterModel()
}