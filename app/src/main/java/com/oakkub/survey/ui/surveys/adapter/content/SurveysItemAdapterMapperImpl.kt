package com.oakkub.survey.ui.surveys.adapter.content

import com.oakkub.survey.ui.surveys.SurveysUiModel

/**
 * Created by oakkub on 25/3/2018 AD.
 */
class SurveysItemAdapterMapperImpl : SurveysItemAdapterMapper {

    override fun map(uiModel: SurveysUiModel): List<SurveysItemAdapterModel> {
        if (uiModel is SurveysUiModel.Loading && uiModel.isFirstTime) {
            return listOf(SurveysItemAdapterModel.Loading)
        }
        if (uiModel !is SurveysUiModel.Success) {
            return emptyList()
        }

        val mappedResponse = uiModel.surveys.map { item -> SurveysItemAdapterModel.Item(item) }
        val adapterModelList = mutableListOf<SurveysItemAdapterModel>()
        adapterModelList.addAll(mappedResponse)

        if (!uiModel.isComplete) {
            adapterModelList.add(SurveysItemAdapterModel.Loading)
        }
        return adapterModelList
    }

}