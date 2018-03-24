package com.oakkub.survey.ui.surveys

import com.oakkub.survey.common.controller.BaseViewModel
import com.oakkub.survey.data.repository.oauth.OAuthRepository
import com.oakkub.survey.data.repository.surveys.SurveysRepository
import javax.inject.Inject

/**
 * Created by oakkub on 24/3/2018 AD.
 */
class SurveysViewModel @Inject constructor(
        val oAuthRepository: OAuthRepository,
        val surveysRepository: SurveysRepository
) : BaseViewModel() {



}