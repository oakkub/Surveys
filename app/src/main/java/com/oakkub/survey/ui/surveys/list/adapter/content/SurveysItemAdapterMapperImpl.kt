package com.oakkub.survey.ui.surveys.list.adapter.content

import com.oakkub.survey.ui.surveys.list.SurveysUiModel

/**
 * Created by oakkub on 25/3/2018 AD.
 */
class SurveysItemAdapterMapperImpl : SurveysItemAdapterMapper {

    override fun map(uiModel: SurveysUiModel): List<SurveysItemAdapterModel> {
        if (uiModel.isLoading && uiModel.isFirstTime) {
            return listOf(SurveysItemAdapterModel.Loading)
        }
        if (uiModel.surveys.isEmpty()) {
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
