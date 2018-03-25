package com.oakkub.survey.data.services.surveys

import com.google.gson.annotations.SerializedName

/**
 * Created by oakkub on 24/3/2018 AD.
 */
data class SurveyResponse(
        @SerializedName("id") val id: String,
        @SerializedName("title") val title: String,
        @SerializedName("description") val description: String,
        @SerializedName("cover_image_url") val lowResImageUrl: String
) {

    val highResCoverImageUrl: String
        get() = lowResImageUrl + "l"

}