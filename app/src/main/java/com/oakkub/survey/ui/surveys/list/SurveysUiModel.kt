package com.oakkub.survey.ui.surveys.list

import com.oakkub.survey.data.services.surveys.SurveyResponse

/**
 * Created by oakkub on 24/3/2018 AD.
 */
sealed class SurveysUiModel {
    data class Loading(val isFirstTime: Boolean) : SurveysUiModel()
    data class Error(val throwable: Throwable) : SurveysUiModel()
    data class Success(val surveys: Set<SurveyResponse>, val isComplete: Boolean) : SurveysUiModel()
}