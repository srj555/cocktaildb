package com.srdroid.cocktail.presentation.drink_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srdroid.cocktail.common.Resource
import com.srdroid.cocktail.domain.use_case.GetDrinkDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DrinkDetailsViewModel @Inject constructor(private val drinkDetailsUseCase: GetDrinkDetailsUseCase) :
    ViewModel() {


    private val _drinkDetails = MutableStateFlow<DrinkDetailsState>(DrinkDetailsState())
    val drinkDetails: StateFlow<DrinkDetailsState> = _drinkDetails


    fun getDrinkDetails(id: String) {
        drinkDetailsUseCase(id).onEach {
            when (it) {
                is Resource.Loading -> {
                    _drinkDetails.value = DrinkDetailsState(isLoading = true)
                }
                is Resource.Error -> {
                    _drinkDetails.value = DrinkDetailsState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _drinkDetails.value = DrinkDetailsState(data = it.data?.get(0))
                }
            }
        }.launchIn(viewModelScope)
    }


}