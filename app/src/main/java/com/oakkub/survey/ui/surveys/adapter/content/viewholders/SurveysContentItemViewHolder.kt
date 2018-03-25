package com.oakkub.survey.ui.surveys.adapter.content.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.eggdigital.trueyouedc.extensions.views.inflate
import com.oakkub.survey.R
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by oakkub on 25/3/2018 AD.
 */
class SurveysContentItemViewHolder private constructor(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    companion object {
        fun create(parent: ViewGroup): SurveysContentItemViewHolder {
            return SurveysContentItemViewHolder(parent.inflate(R.layout.item_surveys_content))
        }
    }

}