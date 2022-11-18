package ru.madmax.testcaseeffectivemobile.featureCart.domain.useCases

import ru.madmax.testcaseeffectivemobile.featureCart.domain.model.Cart
import ru.madmax.testcaseeffectivemobile.featureCart.domain.repository.CartRepository

class GetCartUseCase(
    private val repository: CartRepository
) {
    suspend operator fun invoke(): Cart? {
        return repository.getCart()
    }
}