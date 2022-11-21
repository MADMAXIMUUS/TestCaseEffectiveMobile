package ru.madmax.testcaseeffectivemobile.featureCart.domain.model

data class Basket(
    val id: Int = 0,
    val images: String = "empty",
    val price: Int = 0,
    val title: String = "",
    val amount: Int = 1
)