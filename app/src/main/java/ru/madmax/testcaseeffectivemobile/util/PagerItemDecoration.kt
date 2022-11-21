package ru.madmax.testcaseeffectivemobile.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PagerItemDecoration(
    private val spaceTop: Int,
    private val spaceBottom: Int,
    private val spaceStart: Int,
    private val spaceEnd: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = spaceTop
        outRect.bottom = spaceBottom
        outRect.left = spaceStart
        outRect.right = spaceEnd
    }

}