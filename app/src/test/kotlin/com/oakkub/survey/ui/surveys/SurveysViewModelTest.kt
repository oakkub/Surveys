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
import com.oakkub.survey.ui.surveys.list.SurveysLoadingRequest
import com.oakkub.survey.ui.surveys.list.SurveysUiModel
import com.oakkub.survey.ui.surveys.list.SurveysViewModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.never
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
    private val surveysLoadingRequest = SurveysLoadingRequest(1, 10)
    private val surveyRequest = SurveyRequest(surveysLoadingRequest.page, surveysLoadingRequest.perPage, "")

    @Before
    fun setup() {
        whenever(schedulerProvider.io()) then { Schedulers.trampoline() }
        whenever(oAuthRepository.authenticate(oAuthRequest)) then {
            Single.just(oAuthResponse)
        }

        surveysViewModel = SurveysViewModel(oAuthRepository, surveysRepository, schedulerProvider, oAuthRequest, surveysLoadingRequest)
    }

    @Test
    fun `get surveys should show loading then get response correctly`() {
        val response = List(surveysLoadingRequest.perPage) { SurveyResponse("id:$it", "", "", "") }
        whenever(surveysRepository.getSurveys(surveyRequest)) then {
            Single.just(response)
        }

        surveysViewModel.result.observeForever(observer)
        surveysViewModel.getSurveys()

        verify(oAuthRepository).authenticate(oAuthRequest)
        verify(surveysRepository).getSurveys(surveyRequest)

        verify(observer).onChanged(SurveysUiModel(isFirstTime = true, isLoading = true))
        verify(observer).onChanged(SurveysUiModel(surveys = response.toSet(), isFirstTime = true, isLoading = false))

        assertEquals(response.size, response.toSet().size)
    }

    @Test
    fun `get surveys after it is loading should be ignore`() {
        surveysViewModel.result.observeForever(observer)
        surveysViewModel.result.value = SurveysUiModel(isFirstTime = true, isLoading = true)

        verify(observer).onChanged(SurveysUiModel(isFirstTime = true, isLoading = true))

        surveysViewModel.getSurveys()

        verify(oAuthRepository, never()).authenticate(oAuthRequest)
        verify(surveysRepository, never()).getSurveys(surveyRequest)
    }

    @Test
    fun `get surveys after it is completed should be ignore`() {
        val completedResponse = SurveysUiModel(surveys = setOf(), isComplete = true)
        surveysViewModel.result.observeForever(observer)
        surveysViewModel.result.value = completedResponse

        verify(observer).onChanged(completedResponse)

        surveysViewModel.getSurveys()

        verify(oAuthRepository, never()).authenticate(oAuthRequest)
        verify(surveysRepository, never()).getSurveys(surveyRequest)
    }

    @Test
    fun `get surveys first time should have state loading isFirstTime=true and isLoading=true`() {
        surveysViewModel.result.observeForever(observer)

        whenever(surveysRepository.getSurveys(surveyRequest)) then {
            Single.just(listOf())
        }

        surveysViewModel.getSurveys()

        verify(observer).onChanged(SurveysUiModel(isFirstTime = true, isLoading = true))
    }

    @Test
    fun `get surveys should complete`() {
        surveysViewModel.result.observeForever(observer)

        // Get per page of request and make it looks like it only be able to load half of what we are requesting
        val halfOfWhatRequestingResponse = List(surveysLoadingRequest.perPage / 2) {
            SurveyResponse("$it", "", "", "")
        }
        whenever(surveysRepository.getSurveys(surveyRequest)) then {
            Single.just(halfOfWhatRequestingResponse)
        }

        surveysViewModel.getSurveys()

        val model = SurveysUiModel(isFirstTime = true, isLoading = true)
        verify(observer).onChanged(model)
        verify(observer).onChanged(model.copy(surveys = halfOfWhatRequestingResponse.toSet(), isLoading = false, isComplete = true))
    }

    @Test
    fun `refresh surveys should start loading new item`() {
        surveysViewModel.result.observeForever(observer)

        val response = List(surveysLoadingRequest.perPage) {
            SurveyResponse("$it", "", "", "")
        }
        val responseSecondTime = List(surveysLoadingRequest.perPage) {
            SurveyResponse("$it Second", "", "", "")
        }
        whenever(surveysRepository.getSurveys(surveyRequest)) then {
            Single.just(response)
        } then {
            Single.just(responseSecondTime)
        }

        val model = SurveysUiModel(isFirstTime = true, isLoading = true)
        surveysViewModel.getSurveys()

        verify(observer).onChanged(model)
        verify(observer).onChanged(model.copy(surveys = response.toSet(), isLoading = false))

        surveysViewModel.refresh()

        verify(observer).onChanged(model.copy(isFirstTime = true, isLoading = true))
        verify(observer).onChanged(model.copy(surveys = response.toSet(), isLoading = false))
    }

}
