package com.srdroid.cocktail

import com.srdroid.cocktail.MockResponse.getDrinksModel
import com.srdroid.cocktail.data.repository.DrinkSearchRepositoryImpl
import com.srdroid.cocktail.domain.use_case.SearchDrinksUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class SearchDrinkUseCaseUT {


    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val drinkSearchRepository = mockk<DrinkSearchRepositoryImpl>()
    private val searchDrinksUseCase by lazy {
        SearchDrinksUseCase(
            drinkSearchRepository
        )
    }


    @Test
    fun testDrinkSearchUseCases_getDrinkSearch_Completed() =
        testCoroutineRule.testDispatcher.runBlockingTest {
            coEvery { drinkSearchRepository.getDrinkSearch("s") } returns getDrinksModel()
            val first = searchDrinksUseCase.invoke("s").first()
            assertEquals(first.data?.get(0)?.id,"1")
    }

    @Test
    fun testDrinkSearchUseCases_getDrinkSearch_Failure() =
        testCoroutineRule.testDispatcher.runBlockingTest {
            coEvery { drinkSearchRepository.getDrinkSearch("s") } returns any()
            val first = searchDrinksUseCase.invoke("s").first()
            assertNull(first.data)
        }
}
