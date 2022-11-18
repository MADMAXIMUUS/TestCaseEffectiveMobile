package ru.madmax.testcaseeffectivemobile.featureCart.domain.repository

import ru.madmax.testcaseeffectivemobile.featureCart.domain.model.Cart

interface CartRepository {

    suspend fun getCart(): Cart?

}