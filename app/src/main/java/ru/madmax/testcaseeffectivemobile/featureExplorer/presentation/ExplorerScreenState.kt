package ru.madmax.testcaseeffectivemobile.featureExplorer.presentation

import ru.madmax.testcaseeffectivemobile.featureExplorer.domain.model.Content

data class ExplorerScreenState(
    val content: List<Content> = emptyList(),
    val bestSellerPage: Int = 0,
    val cartAmount: Int = 0,
    val filterMinPrice: Int = 0,
    val filterMaxPrice: Int = 10000,
    val filterBrand: String = "Samsung"
)