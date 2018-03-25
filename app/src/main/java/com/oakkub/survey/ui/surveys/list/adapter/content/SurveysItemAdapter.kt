package com.oakkub.survey.ui.surveys.list.adapter.content

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.oakkub.survey.exceptions.ViewTypeInvalidException
import com.oakkub.survey.ui.surveys.list.adapter.content.viewholders.SurveysContentItemViewHolder
import com.oakkub.survey.ui.surveys.list.adapter.content.viewholders.SurveysProgressItemViewHolder

/**
 * Created by oakkub on 25/3/2018 AD.
 */
class SurveysItemAdapter(private val onClick: OnSurveyItemClickListener) : ListAdapter<SurveysItemAdapterModel, RecyclerView.ViewHolder>(surveysItemAdapterModelDiffUtil) {

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is SurveysItemAdapterModel.Item -> ITEM_TYPE
        is SurveysItemAdapterModel.Loading -> LOADING_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        ITEM_TYPE -> SurveysContentItemViewHolder.create(parent)
        LOADING_TYPE -> SurveysProgressItemViewHolder.create(parent)
        else -> throw ViewTypeInvalidException()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when {
            holder is SurveysContentItemViewHolder && item is SurveysItemAdapterModel.Item -> holder.bind(item, onClick)
        }
    }

    companion object {

        private const val ITEM_TYPE = 1
        private const val LOADING_TYPE = 2

        private val surveysItemAdapterModelDiffUtil = object : DiffUtil.ItemCallback<SurveysItemAdapterModel>() {
            override fun areItemsTheSame(oldItem: SurveysItemAdapterModel, newItem: SurveysItemAdapterModel): Boolean {
                return if (oldItem is SurveysItemAdapterModel.Item && newItem is SurveysItemAdapterModel.Item) {
                    oldItem.surveyResponse.id == newItem.surveyResponse.id
                } else {
                    false
                }
            }

            override fun areContentsTheSame(oldItem: SurveysItemAdapterModel, newItem: SurveysItemAdapterModel): Boolean {
                return oldItem == newItem
            }
        }

    }

}

typealias OnSurveyItemClickListener = (SurveysItemAdapterModel.Item) -> Unit