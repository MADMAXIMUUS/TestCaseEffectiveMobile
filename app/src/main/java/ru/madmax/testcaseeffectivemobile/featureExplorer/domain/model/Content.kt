package ru.madmax.testcaseeffectivemobile.featureExplorer.domain.model

sealed class Content {
    data class HotItem(
        val items: List<HomeStore> = emptyList(),
        val selectedPage: Int = 0
    ) : Content()

    data class BestItem(
        val items: List<BestSeller> = emptyList()
    ) : Content()

    companion object {
        const val HOT_TYPE = 0
        const val BEST_TYPE = 1
    }
}
