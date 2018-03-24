package com.oakkub.survey.data.repository.surveys

import com.oakkub.survey.data.services.surveys.SurveyRequest
import com.oakkub.survey.data.services.surveys.SurveyResponse
import com.oakkub.survey.data.services.surveys.SurveysService
import com.oakkub.survey.ext.then
import com.oakkub.survey.ext.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by oakkub on 24/3/2018 AD.
 */
@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class SurveysRepositoryImplTest {

    @Mock
    lateinit var surveysService: SurveysService

    @Test
    fun `get surveys should success`() {
        val request = SurveyRequest(1, 10, "123456789")
        val response = List(request.size) { SurveyResponse("id:$it", "title:$it", "desc:$it", "http:$it") }
        val repository = SurveysRepositoryImpl(surveysService)

        whenever(surveysService.getSurveys(request)) then {
            Single.just(response)
        }

        repository.getSurveys(request).test()
                .assertValue { assertResponse ->
                    assertResponse.size == request.size && response == assertResponse
                }

        verify(surveysService).getSurveys(request)
    }

    @Test
    fun `get surveys should success with empty`() {
        val request = SurveyRequest(1, 0, "123456789")
        val repository = SurveysRepositoryImpl(surveysService)

        whenever(surveysService.getSurveys(request)) then {
            Single.just(emptyList())
        }

        repository.getSurveys(request).test().assertValue { it.isEmpty() }

        verify(surveysService).getSurveys(request)
    }

}