package com.oakkub.survey.ui.surveys.list

import com.oakkub.survey.data.services.surveys.SurveyResponse

/**
 * Created by oakkub on 24/3/2018 AD.
 */
data class SurveysUiModel(val surveys: Set<SurveyResponse> = emptySet(),
                          val isFirstTime: Boolean = false,
                          val isLoading: Boolean = false,
                          val isComplete: Boolean = false,
                          val error: Throwable? = null)
