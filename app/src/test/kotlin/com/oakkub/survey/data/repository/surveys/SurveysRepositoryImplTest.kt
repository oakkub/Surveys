package com.oakkub.survey.data.repository.surveys

import com.oakkub.survey.data.services.surveys.SurveysService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
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
    fun `get surveys should sucess`() {
        val repository = SurveysRepositoryImpl(surveysService)

    }

}