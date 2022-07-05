package com.srdroid.cocktail.presentation.drink_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srdroid.cocktail.common.Resource
import com.srdroid.cocktail.domain.model.Drink
import com.srdroid.cocktail.domain.use_case.SearchDrinksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class DrinkSearchViewModel @Inject constructor(
    private val searchDrinksUseCase: SearchDrinksUseCase
) : ViewModel() {


    private val _drinkSearchList = MutableStateFlow(DrinkSearchState())
    val drinkSearchList: StateFlow<DrinkSearchState> = _drinkSearchList
    private lateinit var _memesList:List<Drink>

    fun searchDrink(s: String) {
        searchDrinksUseCase(s).onStart {
            // Loading State
            _drinkSearchList.value = DrinkSearchState(isLoading = true)
        }.onEach {
            when (it) {
                is Resource.Success -> {
                    _memesList = it.data?: listOf()
                    _drinkSearchList.value = DrinkSearchState(data = _memesList)
                }
                is Resource.Error -> {
                    _drinkSearchList.value = DrinkSearchState(error = it.message ?: "")
                }
                else -> _drinkSearchList.value = DrinkSearchState(error = it.message ?: "")
            }
        }.launchIn(viewModelScope)
    }

    fun filterDrink(s: String) {
        // filter data
        val filteredData = _memesList.filter {
            it.name.lowercase().contains(s.lowercase())
        }
        // update data
        _drinkSearchList.value =
            DrinkSearchState(data = filteredData)
    }


}