package ru.madmax.testcaseeffectivemobile.featureExplorer.presentation.viewHolder

import ru.madmax.testcaseeffectivemobile.featureExplorer.domain.model.Content
import ru.madmax.testcaseeffectivemobile.featureExplorer.presentation.adapter.BestAdapter

interface MainListHolder {
    fun bind(item: Content, listener: BestAdapter.OnItemClickListener)
    fun onAttach()
    fun onDetach()
}