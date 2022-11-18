package ru.madmax.testcaseeffectivemobile.featureCart.domain.model

data class Cart(
    val basket: List<Basket> = emptyList(),
    val delivery: String = "",
    val id: String = "",
    val total: Int = 0
)