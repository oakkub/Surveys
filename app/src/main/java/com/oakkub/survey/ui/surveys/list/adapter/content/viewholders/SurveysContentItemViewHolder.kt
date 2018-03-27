package com.oakkub.survey.ui.surveys.list.adapter.content.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.oakkub.survey.extensions.inflate
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.request.ImageRequest
import com.oakkub.survey.R
import com.oakkub.survey.ui.surveys.list.adapter.content.OnSurveyItemClickListener
import com.oakkub.survey.ui.surveys.list.adapter.content.SurveysItemAdapterModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_surveys_content.*

/**
 * Created by oakkub on 25/3/2018 AD.
 */
class SurveysContentItemViewHolder private constructor(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: SurveysItemAdapterModel.Item, onSurveyItemClickListener: OnSurveyItemClickListener) {
        surveysItemButton.setOnClickListener {
            onSurveyItemClickListener(item)
        }

        val response = item.surveyResponse
        surveysItemTitleTextView.text = response.title
        surveysItemDescriptionTextView.text = response.description
        loadImage(response.highResCoverImageUrl, response.lowResImageUrl)
    }

    private fun loadImage(highResUrl: String, lowResUrl: String) {
        val newController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(ImageRequest.fromUri(highResUrl))
                .setLowResImageRequest(ImageRequest.fromUri(lowResUrl))
                .setOldController(surveysItemCoverImageView.controller)
                .build()
        surveysItemCoverImageView.controller = newController
    }

    companion object {
        fun create(parent: ViewGroup): SurveysContentItemViewHolder {
            return SurveysContentItemViewHolder(parent.inflate(R.layout.item_surveys_content))
        }
    }

}