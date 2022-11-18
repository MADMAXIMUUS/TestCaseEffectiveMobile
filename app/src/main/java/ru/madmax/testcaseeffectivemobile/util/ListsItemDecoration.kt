package ru.madmax.testcaseeffectivemobile.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ListsItemDecoration(
    private val spaceTop: Int,
    private val spaceBottom: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position == -1) return
        if (position == 0) {
            outRect.top = spaceTop * 2
            outRect.bottom = spaceBottom
        } else {
            outRect.bottom = spaceBottom
            outRect.top = spaceTop
        }
    }
}