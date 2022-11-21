package ru.madmax.testcaseeffectivemobile.featureCart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.madmax.testcaseeffectivemobile.featureCart.domain.model.Basket
import ru.madmax.testcaseeffectivemobile.featureCart.domain.model.Cart
import ru.madmax.testcaseeffectivemobile.featureCart.domain.useCases.GetCartUseCase
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(Cart())
    val uiState = _uiState.asStateFlow()

    init {
        getCart()
    }

    private fun getCart() {
        viewModelScope.launch {
            getCartUseCase()?.also { cart ->
                _uiState.update { currentState ->
                    currentState.copy(
                        id = cart.id,
                        basket = cart.basket,
                        delivery = cart.delivery,
                        total = cart.total
                    )
                }
            }
        }
    }

    fun updateAmount(position: Int, addCount: Int) {
        val newAmount = uiState.value.basket[position].amount + addCount
        var total = 0
        val list = mutableListOf<Basket>()
        uiState.value.basket.forEachIndexed { index, basket ->
            if (index == position) {
                if (newAmount != 0) {
                    list.add(
                        basket.copy(
                            amount = newAmount
                        )
                    )
                    total += basket.price * newAmount
                }
            } else {
                list.add(basket)
                total += basket.price * basket.amount
            }
        }
        _uiState.update { currentState ->
            currentState.copy(
                basket = list,
                total = total
            )
        }
    }

}