package com.oakkub.survey.ui.surveys

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.oakkub.survey.common.thread.SchedulerProvider
import com.oakkub.survey.data.repository.oauth.OAuthRepository
import com.oakkub.survey.data.repository.surveys.SurveysRepository
import com.oakkub.survey.data.services.oauth.OAuthRequest
import com.oakkub.survey.data.services.oauth.OAuthResponse
import com.oakkub.survey.data.services.surveys.SurveyRequest
import com.oakkub.survey.data.services.surveys.SurveyResponse
import com.oakkub.survey.ext.then
import com.oakkub.survey.ext.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by oakkub on 24/3/2018 AD.
 */
@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class SurveysViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<SurveysUiModel>

    @Mock
    lateinit var surveysRepository: SurveysRepository

    @Mock
    lateinit var oAuthRepository: OAuthRepository

    @Mock
    lateinit var schedulerProvider: SchedulerProvider


    private lateinit var surveysViewModel: SurveysViewModel

    private val oAuthRequest = OAuthRequest("", "", "")
    private val oAuthResponse = OAuthResponse("", "", -1, -1)
    private val surveysLoadingRequest  = SurveysLoadingRequest(1, 10)

    @Before
    fun setup() {
        whenever(schedulerProvider.io()) then { Schedulers.trampoline() }

        surveysViewModel = SurveysViewModel(oAuthRepository, surveysRepository, schedulerProvider, oAuthRequest, surveysLoadingRequest)
    }

    @Test
    fun `get surveys should return correctly`() {
        whenever(oAuthRepository.authenticate(oAuthRequest)) then {
            Single.just(oAuthResponse)
        }

        val response = List(10) { SurveyResponse("", "", "", "") }
        val surveyRequest = SurveyRequest(surveysLoadingRequest.page, surveysLoadingRequest.perPage, "")
        whenever(surveysRepository.getSurveys(surveyRequest)) then {
            Single.just(response)
        }

        surveysViewModel.result.observeForever(observer)
        surveysViewModel.getSurveys()
        verify(oAuthRepository).authenticate(oAuthRequest)
        verify(surveysRepository).getSurveys(surveyRequest)

        verify(observer).onChanged(SurveysUiModel.Loading)
        verify(observer).onChanged(SurveysUiModel.Success(response.toSet(), false))
    }

}