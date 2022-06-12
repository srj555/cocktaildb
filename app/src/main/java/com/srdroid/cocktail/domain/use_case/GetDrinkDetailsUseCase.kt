package com.srdroid.cocktail.domain.use_case

import com.srdroid.cocktail.common.Resource
import com.srdroid.cocktail.data.model.toDomainDrinkDetails
import com.srdroid.cocktail.domain.model.DrinkDetails
import com.srdroid.cocktail.domain.repository.DrinkDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDrinkDetailsUseCase @Inject constructor(private val repository: DrinkDetailsRepository) {

    operator fun invoke(id: String): Flow<Resource<List<DrinkDetails>>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getDrinkDetails(id)
            val domainData =
                if (!data.drinks.isNullOrEmpty()) data.drinks.map { it.toDomainDrinkDetails() } else emptyList()
            emit(Resource.Success(data = domainData))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {

        }
    }


}