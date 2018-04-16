package com.oakkub.survey.ui.surveys.list.adapter.dot

/**
 * Created by oakkub on 25/3/2018 AD.
 */
interface DotIndicatorItemMapper {
    fun map(totalItem: Int, selectedPosition: Int): List<DotIndicatorItemModel>
}
