package com.oakkub.survey.ui.surveys.list.adapter.content.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.oakkub.survey.extensions.inflate
import com.oakkub.survey.R
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by oakkub on 25/3/2018 AD.
 */
class SurveysProgressItemViewHolder private constructor(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    companion object {
        fun create(parent: ViewGroup): SurveysProgressItemViewHolder {
            val view = parent.inflate(R.layout.item_surveys_loading)
            return SurveysProgressItemViewHolder(view)
        }
    }

}
