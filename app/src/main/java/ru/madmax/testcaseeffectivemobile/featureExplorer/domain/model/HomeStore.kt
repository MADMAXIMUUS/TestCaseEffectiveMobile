package ru.madmax.testcaseeffectivemobile.featureExplorer.domain.model

data class HomeStore(
    val id: Int = 0,
    val is_buy: Boolean = false,
    val is_new: Boolean = false,
    val picture: String = "empty",
    val subtitle: String = "",
    val title: String = ""
)
