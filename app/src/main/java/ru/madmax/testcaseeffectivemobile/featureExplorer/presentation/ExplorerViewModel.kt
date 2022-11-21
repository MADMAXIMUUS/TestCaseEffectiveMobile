package ru.madmax.testcaseeffectivemobile.featureExplorer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.madmax.testcaseeffectivemobile.featureExplorer.domain.model.Content
import ru.madmax.testcaseeffectivemobile.featureExplorer.domain.useCases.GetItemsUseCase
import javax.inject.Inject

@HiltViewModel
class ExplorerViewModel @Inject constructor(
    val getItemsUseCase: GetItemsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExplorerScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        getItems()
    }

    private fun getItems() {
        viewModelScope.launch {
            getItemsUseCase().also { explorer ->
                if (explorer != null) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            content = listOf(
                                Content.HotItem(
                                    explorer.home_store
                                ),
                                Content.BestItem(
                                    explorer.best_seller
                                )
                            )
                        )
                    }
                }
            }
        }
    }

    fun updateFilter(brand: String, priceMin: Int, priceMax: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                filterBrand = brand,
                filterMinPrice = priceMin,
                filterMaxPrice = priceMax
            )
        }
    }

    fun updateCartAmount() {
        _uiState.update { currentState ->
            currentState.copy(
                cartAmount = currentState.cartAmount + 1
            )
        }
    }

}