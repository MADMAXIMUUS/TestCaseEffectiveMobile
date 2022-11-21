package ru.madmax.testcaseeffectivemobile.featureDetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.madmax.testcaseeffectivemobile.featureCart.domain.model.Cart
import ru.madmax.testcaseeffectivemobile.featureDetails.domain.model.Details
import ru.madmax.testcaseeffectivemobile.featureDetails.domain.useCases.GetDetailsUseCase
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(Details())
    val uiState = _uiState.asStateFlow()

    init {
        getCart()
    }

    private fun getCart() {
        viewModelScope.launch {
            getDetailsUseCase()?.also { details ->
                _uiState.update { currentState ->
                    currentState.copy(
                        CPU = details.CPU,
                        camera = details.camera,
                        capacity = details.capacity,
                        color = details.color,
                        id = details.id,
                        images = details.images,
                        isFavorites = details.isFavorites,
                        price = details.price,
                        rating = details.rating,
                        sd = details.sd,
                        ssd = details.ssd,
                        title = details.title
                    )
                }
            }
        }
    }
}