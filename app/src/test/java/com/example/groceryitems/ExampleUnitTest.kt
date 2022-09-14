package com.example.groceryitems

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PersistenceInstrumentationTests {

    @Before
    fun setup() {
        launchActivity<MainActivity>()
//        onView(withId(R.id.add_grocery).perform(click())
//
//        onView(withId(R.id.name_input)).perform(replaceText("Name"))
//        onView(withId(R.id.qty_input)).perform(replaceText("3"))
//        onView(withId(R.id.price_input)).perform(replaceText("12"))
//        onView(withId(R.id.save)).perform(click()))
    }

    inline fun <reified A : Activity> launchActivity(
        intent: Intent? = null,
        activityOptions: Bundle? = null
    ): ActivityScenario<A> =
        when (intent) {
            null -> ActivityScenario.launch(A::class.java, activityOptions)
            else -> ActivityScenario.launch(intent, activityOptions)
        }

    @Test
    fun new_forageable_is_displayed_in_list() {
        onView(withText("Name")).check(matches(isDisplayed()))
        onView(withText("3")).check(matches(isDisplayed()))
        onView(withText("Rs.12.0")).check(matches(isDisplayed()))
        onView(withText("Rs.36.0")).check(matches(isDisplayed()))
    }

    @Test
    fun delete_new_forageable() {
        onView(withText("Name")).perform(click())
        onView(withId(R.id.delete_icon)).perform(click())
        Thread.sleep(1000)
        onView(withText("Name")).check(doesNotExist())
    }
}
