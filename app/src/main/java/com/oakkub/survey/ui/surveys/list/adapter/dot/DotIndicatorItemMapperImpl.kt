package com.oakkub.survey.ui.surveys.list.adapter.dot

/**
 * Created by oakkub on 25/3/2018 AD.
 */
class DotIndicatorItemMapperImpl : DotIndicatorItemMapper {

    override fun map(totalItem: Int, selectedPosition: Int): List<DotIndicatorItemModel> {
        return List(totalItem) { position ->
            DotIndicatorItemModel(orderNo = position, isSelected = position == selectedPosition)
        }
    }

}
