package com.oakkub.survey.ui.surveys

import com.oakkub.survey.data.services.surveys.SurveyResponse

/**
 * Created by oakkub on 24/3/2018 AD.
 */
sealed class SurveysUiModel {
    object Loading: SurveysUiModel()
    object Empty: SurveysUiModel()
    data class Error(val throwable: Throwable): SurveysUiModel()
    data class Success(val surveys: List<SurveyResponse>): SurveysUiModel()
}