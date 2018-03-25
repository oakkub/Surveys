package com.oakkub.survey.widgets.recyclerview

import android.graphics.Rect
import android.support.annotation.Px
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View


/**
 * Created by oakkub on 10/3/2017 AD.
 */
class LinearOffsetItemDecoration(@param:Px private val space: Int,
                                 private val shouldIncludeEdge: Boolean) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.layoutManager is GridLayoutManager ||
                parent.layoutManager is StaggeredGridLayoutManager) {
            return
        }
        val layoutManager = parent.layoutManager as LinearLayoutManager

        val adapterPosition = parent.getChildAdapterPosition(view)
        if (adapterPosition == RecyclerView.NO_POSITION) {
            return
        }

        if (layoutManager.orientation == LinearLayoutManager.VERTICAL) {
            outRect.top = if (!shouldIncludeEdge && adapterPosition == 0) 0 else space
            outRect.left = if (shouldIncludeEdge) space else 0
            outRect.right = if (shouldIncludeEdge) space else 0
            outRect.bottom = if (shouldIncludeEdge && isLastPosition(layoutManager, adapterPosition)) space else 0
        } else {
            outRect.top = if (shouldIncludeEdge) space else 0
            outRect.left = if (!shouldIncludeEdge && adapterPosition == 0) 0 else space
            outRect.right = if (shouldIncludeEdge && isLastPosition(layoutManager, adapterPosition)) space else 0
            outRect.bottom = if (shouldIncludeEdge) space else 0
        }
    }

    private fun isLastPosition(layoutManager: LinearLayoutManager, itemPosition: Int): Boolean {
        return itemPosition == layoutManager.itemCount - 1
    }
}