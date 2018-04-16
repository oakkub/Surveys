package com.oakkub.survey.ui.surveys.list.adapter.dot

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import com.oakkub.survey.ui.surveys.list.adapter.dot.viewholders.DotViewHolder

/**
 * Created by oakkub on 25/3/2018 AD.
 */
class DotIndicatorItemAdapter : ListAdapter<DotIndicatorItemModel, DotViewHolder>(dotIndicatorDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DotViewHolder {
        return DotViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DotViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {

        private val dotIndicatorDiffUtil = object : DiffUtil.ItemCallback<DotIndicatorItemModel>() {
            override fun areItemsTheSame(oldItem: DotIndicatorItemModel, newItem: DotIndicatorItemModel): Boolean {
                return oldItem.orderNo == newItem.orderNo
            }

            override fun areContentsTheSame(oldItem: DotIndicatorItemModel, newItem: DotIndicatorItemModel): Boolean {
                return oldItem == newItem
            }
        }

    }

}
