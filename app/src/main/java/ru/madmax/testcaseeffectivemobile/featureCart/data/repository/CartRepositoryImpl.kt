package ru.madmax.testcaseeffectivemobile.featureCart.data.repository

import ru.madmax.testcaseeffectivemobile.featureCart.data.dataSource.CartApi
import ru.madmax.testcaseeffectivemobile.featureCart.domain.model.Cart
import ru.madmax.testcaseeffectivemobile.featureCart.domain.repository.CartRepository

class CartRepositoryImpl(
    private val cartApi: CartApi
): CartRepository {

    override suspend fun getCart(): Cart?{
        return cartApi.getCart()
    }

}