package com.klt.gbs.ui.popular

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.klt.gbs.R
import com.klt.gbs.ui.main.MainActivity
import junit.framework.TestCase
import org.hamcrest.Matchers.anything
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class PopularFragmentTest : TestCase(){

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun clickItem(){
        // Directly specify the position in the adapter to click on
        onData(anything()) // We are using the position so don't need to specify a data matcher
            .inAdapterView(withId(R.id.item_list)) // Specify the explicit id of the ListView
            .atPosition(1) // Explicitly specify the adapter item to use
            .perform(click()); // Standard ViewAction
    }

}