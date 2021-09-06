package com.burak.myheroes.util

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by mburak on 5.09.2021.
 */
abstract class EndlessScrollListener: RecyclerView.OnScrollListener() {
    private var visibleThreshold = 3

    private var firstVisibleItemPosition = 0
    private var visibleItemCount:Int = 0
    private var totalItemCount:Int = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy == 0) {
            return
        }

        val layoutManager = recyclerView.layoutManager

        visibleItemCount = recyclerView.childCount
        totalItemCount = layoutManager!!.itemCount

        firstVisibleItemPosition = when (layoutManager) {
            is GridLayoutManager -> (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            is LinearLayoutManager -> layoutManager.findFirstVisibleItemPosition()
            else -> return
        }

        if (!isLoading() && (totalItemCount - visibleItemCount)
            <= (firstVisibleItemPosition + visibleThreshold)) {
            // End has been reached
            onLoadMore()
        }
    }

    abstract fun onLoadMore()

    abstract fun isLoading(): Boolean
}