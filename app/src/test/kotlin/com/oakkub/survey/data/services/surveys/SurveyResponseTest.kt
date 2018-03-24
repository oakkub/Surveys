package com.oakkub.survey.data.services.surveys

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by oakkub on 24/3/2018 AD.
 */
@Suppress("IllegalIdentifier")
class SurveyResponseTest {

    @Test
    fun `SurveyResponse's high resolution cover image should append with 'l'`() {
        val lowResUrl = "https://someimage/awesomeimage1"
        val response = SurveyResponse(id = "", title = "", description = "", lowResImageUrl = lowResUrl)

        assertEquals(response.highResCoverImageUrl, lowResUrl + "l")
    }

}