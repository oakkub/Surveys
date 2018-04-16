package com.oakkub.survey.ui.surveys.list.adapter.dot.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.oakkub.survey.extensions.inflate
import com.oakkub.survey.R
import com.oakkub.survey.ui.surveys.list.adapter.dot.DotIndicatorItemModel
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by oakkub on 25/3/2018 AD.
 */
class DotViewHolder private constructor(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(model: DotIndicatorItemModel) {
        containerView.isSelected = model.isSelected
    }

    companion object {
        fun create(parent: ViewGroup): DotViewHolder {
            return DotViewHolder(parent.inflate(R.layout.item_dot))
        }
    }

}
