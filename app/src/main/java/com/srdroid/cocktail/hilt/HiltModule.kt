package com.srdroid.cocktail.hilt

import com.srdroid.cocktail.common.AppConstants
import com.srdroid.cocktail.data.api.DrinkSearchAPI
import com.srdroid.cocktail.data.repository.DrinkDetailsRepositoryImpl
import com.srdroid.cocktail.data.repository.DrinkSearchRepositoryImpl
import com.srdroid.cocktail.domain.repository.DrinkDetailsRepository
import com.srdroid.cocktail.domain.repository.DrinkSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltModule {


    @Provides
    @Singleton
    fun provideDrinkSearchAPI(): DrinkSearchAPI {
        return Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(DrinkSearchAPI::class.java)
    }


    @Provides
    fun provideDrinkSearchRepository(drinkSearchAPI: DrinkSearchAPI): DrinkSearchRepository {
        return DrinkSearchRepositoryImpl(drinkSearchAPI)
    }


    @Provides
    fun provideDrinkDetails(searchDrinkSearchAPI: DrinkSearchAPI): DrinkDetailsRepository {
        return DrinkDetailsRepositoryImpl(searchDrinkSearchAPI)
    }


}