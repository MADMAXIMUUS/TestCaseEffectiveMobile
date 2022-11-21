package ru.madmax.testcaseeffectivemobile.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ListsMainItemDecoration(
    private val spaceTop: Int,
    private val spaceBottom: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when (parent.getChildAdapterPosition(view)) {
            parent.adapter?.itemCount!! - 1 -> {
                outRect.top = spaceTop
                outRect.bottom = 330
            }
            0 -> {
                outRect.top = 0
                outRect.bottom = spaceBottom
            }
            else -> {
                outRect.bottom = spaceBottom
                outRect.top = spaceTop
            }
        }
    }
}