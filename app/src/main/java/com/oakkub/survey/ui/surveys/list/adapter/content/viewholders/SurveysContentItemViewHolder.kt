package com.oakkub.survey.ui.surveys.list.adapter.content.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.eggdigital.trueyouedc.extensions.views.inflate
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
        surveysItemCoverImageView.setImageURI(response.highResCoverImageUrl)
        surveysItemTitleTextView.text = response.title
        surveysItemDescriptionTextView.text = response.description
    }

    companion object {
        fun create(parent: ViewGroup): SurveysContentItemViewHolder {
            return SurveysContentItemViewHolder(parent.inflate(R.layout.item_surveys_content))
        }
    }

}