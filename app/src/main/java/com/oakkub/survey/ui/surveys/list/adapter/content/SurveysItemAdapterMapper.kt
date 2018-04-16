package com.oakkub.survey.ui.surveys.list.adapter.content

import com.oakkub.survey.ui.surveys.list.SurveysUiModel

/**
 * Created by oakkub on 25/3/2018 AD.
 */
interface SurveysItemAdapterMapper {
    fun map(uiModel: SurveysUiModel): List<SurveysItemAdapterModel>
}
