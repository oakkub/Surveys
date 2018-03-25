package com.oakkub.survey.ui.surveys.list

import android.arch.lifecycle.MutableLiveData
import com.oakkub.survey.common.controller.BaseViewModel
import com.oakkub.survey.common.thread.SchedulerProvider
import com.oakkub.survey.data.repository.oauth.OAuthRepository
import com.oakkub.survey.data.repository.surveys.SurveysRepository
import com.oakkub.survey.data.services.oauth.OAuthRequest
import com.oakkub.survey.data.services.surveys.SurveyRequest
import com.oakkub.survey.data.services.surveys.SurveyResponse
import com.oakkub.survey.extensions.plusAssign
import javax.inject.Inject


/**
 * Created by oakkub on 24/3/2018 AD.
 */
class SurveysViewModel @Inject constructor(
        private val oAuthRepository: OAuthRepository,
        private val surveysRepository: SurveysRepository,
        private val schedulerProvider: SchedulerProvider,
        private val oAuthRequest: OAuthRequest,
        private var surveysLoadingRequest: SurveysLoadingRequest
) : BaseViewModel() {

    val result = MutableLiveData<SurveysUiModel>()

    private var surveysItem = setOf<SurveyResponse>()

    fun getSurveys() {
        getSurveys(surveysLoadingRequest, oAuthRequest, false)
    }

    fun refresh() {
        getSurveys(surveysLoadingRequest.copy(page = 1), oAuthRequest, true)
    }

    private fun getSurveys(surveysLoadingRequest: SurveysLoadingRequest, oAuthRequest: OAuthRequest, shouldRefresh: Boolean) {
        val currentState = result.value
        if (currentState != null) {
            when {
                currentState.isLoading -> return
                currentState.isComplete && !shouldRefresh -> return
            }
        }
        result.value = if (result.value == null) {
            SurveysUiModel(isFirstTime = true, isLoading = true, isComplete = false)
        } else {
            result.value!!.copy(isFirstTime = surveysLoadingRequest.page == 1, isLoading = true)
        }

        val (page, perPage) = surveysLoadingRequest

        disposable += oAuthRepository.authenticate(oAuthRequest)
                .map { oAuthResponse -> SurveyRequest(page, perPage, oAuthResponse.accessToken) }
                .flatMap { request -> surveysRepository.getSurveys(request) }
                .subscribeOn(schedulerProvider.io())
                .subscribe({ surveysResponse ->
                    if (shouldRefresh(surveysLoadingRequest)) {
                        surveysItem = setOf()
                    }

                    if (shouldLoadMore(surveysResponse, perPage)) {
                        this.surveysLoadingRequest = surveysLoadingRequest.copy(page = page + 1)
                    }

                    surveysItem += surveysResponse.toSet()
                    result.postValue(result.value?.copy(
                            surveys = surveysItem,
                            isComplete = didLoadComplete(surveysResponse, perPage),
                            isLoading = false))
                }, { throwable ->
                    result.postValue(result.value?.copy(error = throwable, isLoading = false))
                })
    }

    private fun shouldRefresh(surveysLoadingRequest: SurveysLoadingRequest) = surveysLoadingRequest.page == 1

    private fun shouldLoadMore(surveysResponse: List<SurveyResponse>, perPage: Int) = surveysResponse.size == perPage

    private fun didLoadComplete(surveysResponse: List<SurveyResponse>, perPage: Int) = surveysResponse.size < perPage

}