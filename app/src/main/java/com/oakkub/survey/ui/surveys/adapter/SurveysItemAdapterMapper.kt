package com.oakkub.survey.ui.surveys.adapter

import com.oakkub.survey.ui.surveys.SurveysUiModel

/**
 * Created by oakkub on 25/3/2018 AD.
 */
interface SurveysItemAdapterMapper {
    fun map(uiModel: SurveysUiModel): List<SurveysItemAdapterModel>
}