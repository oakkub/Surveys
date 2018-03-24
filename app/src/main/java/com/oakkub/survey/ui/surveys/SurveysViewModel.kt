package com.oakkub.survey.ui.surveys

import android.arch.lifecycle.MutableLiveData
import com.oakkub.survey.common.controller.BaseViewModel
import com.oakkub.survey.common.thread.SchedulerProvider
import com.oakkub.survey.data.repository.oauth.OAuthRepository
import com.oakkub.survey.data.repository.surveys.SurveysRepository
import com.oakkub.survey.data.services.oauth.OAuthRequest
import com.oakkub.survey.data.services.surveys.SurveyRequest
import com.oakkub.survey.data.services.surveys.SurveyResponse
import com.oakkub.survey.extensions.plusAssign



/**
 * Created by oakkub on 24/3/2018 AD.
 */
class SurveysViewModel constructor(
        private val oAuthRepository: OAuthRepository,
        private val surveysRepository: SurveysRepository,
        private val schedulerProvider: SchedulerProvider,
        private val oAuthRequest: OAuthRequest,
        private var surveysLoadingRequest: SurveysLoadingRequest
) : BaseViewModel() {

    val result = MutableLiveData<SurveysUiModel>()

    private var surveysItem = setOf<SurveyResponse>()

    fun getSurveys() {
        getSurveys(surveysLoadingRequest, oAuthRequest)
    }

    private fun getSurveys(surveysLoadingRequest: SurveysLoadingRequest, oAuthRequest: OAuthRequest) {
        val currentState = result.value
        when (currentState) {
            is SurveysUiModel.Loading -> return
            is SurveysUiModel.Success -> if (currentState.isComplete) return
        }
        result.value = SurveysUiModel.Loading

        val (page, perPage) = surveysLoadingRequest

        disposable += oAuthRepository.authenticate(oAuthRequest)
                .map { oAuthResponse -> SurveyRequest(page, perPage, oAuthResponse.accessToken) }
                .flatMap { request -> surveysRepository.getSurveys(request) }
                .subscribeOn(schedulerProvider.io())
                .subscribe({ surveysResponse ->
                    if (shouldLoadMore(surveysResponse, perPage)) {
                        this.surveysLoadingRequest = surveysLoadingRequest.copy(page = page + 1)
                    }

                    surveysItem += surveysResponse.toSet()
                    result.postValue(SurveysUiModel.Success(surveysItem, didLoadComplete(surveysResponse, perPage)))
                }, { throwable ->
                    result.postValue(SurveysUiModel.Error(throwable))
                })
    }

    private fun shouldLoadMore(surveysResponse: List<SurveyResponse>, perPage: Int) = surveysResponse.size == perPage

    private fun didLoadComplete(surveysResponse: List<SurveyResponse>, perPage: Int) = surveysResponse.size < perPage

}