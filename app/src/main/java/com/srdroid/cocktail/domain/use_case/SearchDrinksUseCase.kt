package com.srdroid.cocktail.domain.use_case

import com.srdroid.cocktail.common.Resource
import com.srdroid.cocktail.data.model.toDomainDrink
import com.srdroid.cocktail.domain.model.Drink
import com.srdroid.cocktail.domain.repository.DrinkSearchRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ExperimentalCoroutinesApi
class SearchDrinksUseCase @Inject constructor(private val repository: DrinkSearchRepository) {

    operator fun invoke(q: String): Flow<Resource<List<Drink>>> = channelFlow {
        try {
            val data =
                repository.getDrinkSearch(q)
            val domainData =
                if (data.drinks != null) data.drinks.map { it -> it.toDomainDrink() } else emptyList()
            send(Resource.Success(data = domainData))
        } catch (e: HttpException) {
            send(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            send(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {
            send(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        }
    }


}