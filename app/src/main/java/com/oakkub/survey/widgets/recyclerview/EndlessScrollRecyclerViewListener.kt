package com.oakkub.survey.widgets.recyclerview

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

/**
 * Created by oakkub on 25/3/2018 AD.
 */
class EndlessScrollRecyclerViewListener(private val visibleThreshold: Int,
                                        private val onLoadMore: (itemCount: Int) -> Unit) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (dy < 0) {
            // Exit if scroll up
            return
        }

        val layoutManager = recyclerView.layoutManager
        val visibleThreshold = calculateVisibleThresholdByLayoutManager(layoutManager, visibleThreshold)

        val lastVisibleItemPosition = findLastVisibleItemPosition(layoutManager)
        val itemCount = layoutManager.itemCount

        if (lastVisibleItemPosition + 1 + visibleThreshold >= itemCount) {
            onLoadMore(itemCount)
        }
    }

    private fun calculateVisibleThresholdByLayoutManager(layoutManager: RecyclerView.LayoutManager, defaultVisibleThreshold: Int): Int {
        if (layoutManager is GridLayoutManager) {
            return defaultVisibleThreshold * layoutManager.spanCount
        }

        if (layoutManager is StaggeredGridLayoutManager) {
            return defaultVisibleThreshold * layoutManager.spanCount
        }

        return defaultVisibleThreshold
    }

    private fun findLastVisibleItemPosition(layoutManager: RecyclerView.LayoutManager?): Int {
        return if (layoutManager is StaggeredGridLayoutManager) {
            layoutManager.findLastVisibleItemPositions(null).max() ?: 0
        } else {
            (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        }
    }

}
