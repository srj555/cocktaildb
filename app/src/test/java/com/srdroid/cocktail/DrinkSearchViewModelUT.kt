package com.srdroid.cocktail

import com.srdroid.cocktail.domain.repository.DrinkSearchRepository
import com.srdroid.cocktail.domain.use_case.SearchDrinksUseCase
import com.srdroid.cocktail.presentation.drink_search.DrinkSearchViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.anyOrNull

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class DrinkSearchViewModelUT {


    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val drinkSearchRepository = mockk<DrinkSearchRepository>()
    private val searchDrinksUseCase by lazy {
        SearchDrinksUseCase(
            drinkSearchRepository
        )
    }

    private lateinit var drinkSearchViewModel: DrinkSearchViewModel

    @Before
    fun setUp() {
        drinkSearchViewModel = DrinkSearchViewModel(searchDrinksUseCase)
    }

    @Test
    fun testDrinkSearchViewModel_searchDrink_success() {
        testCoroutineRule.testDispatcher.runBlockingTest {
            coEvery { drinkSearchRepository.getDrinkSearch("s") } returns MockResponse.getDrinksModel()
            drinkSearchViewModel.searchDrink("s")
            assertEquals(drinkSearchViewModel.drinkSearchList.value.data?.get(0)?.id, "1")
        }
    }

    @Test
    fun testDrinkSearchViewModel_searchDrink_failure() {
        testCoroutineRule.testDispatcher.runBlockingTest {
            coEvery { drinkSearchRepository.getDrinkSearch("s") } returns anyOrNull()
            drinkSearchViewModel.searchDrink("s")
            assertNotNull(drinkSearchViewModel.drinkSearchList.value.error)
        }
    }
}
