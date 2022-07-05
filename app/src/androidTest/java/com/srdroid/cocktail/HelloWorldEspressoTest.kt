package com.srdroid.cocktail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.srdroid.cocktail.presentation.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class HelloWorldEspressoTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

   /* @Test
    fun listGoesOverTheFold() {
        onView(withText("Cocktail Db")).check(matches(isDisplayed()))
    }*/

    @Test
    fun changeText_sameActivity() {
        Thread.sleep(5000L)
        onView(withId(R.id.menuSearch)).perform(click())
        // Check that the text was changed.
        //onView(withId(R.id.textToBeChanged)).check(matches(withText(STRING_TO_BE_TYPED)))
    }
}