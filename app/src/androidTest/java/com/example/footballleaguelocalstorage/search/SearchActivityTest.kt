package com.example.footballleaguelocalstorage.search

import android.view.KeyEvent
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.footballleaguelocalstorage.R
import com.example.footballleaguelocalstorage.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchActivityTest {

    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun searchMatchNotFound(){
        //Fragment League
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        //Detail League
        onView(withId(R.id.search)).perform(click())

        //Search Match
        onView(withId(R.id.searchMatch)).check(matches(isDisplayed()))
        onView(withId(R.id.searchMatch)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(typeText("Barcelona"), pressImeActionButton())
        Thread.sleep(5000)

        //Check Text No Match
        onView(withId(R.id.textNoData)).check(matches(isDisplayed()))
    }

    @Test
    fun searchMatchFound(){
        //Fragment League
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        //Detail League
        onView(withId(R.id.search)).perform(click())

        //SearchMatch
        onView(withId(R.id.searchMatch)).check(matches(isDisplayed()))
        onView(withId(R.id.searchMatch)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(typeText("Arsenal"), pressImeActionButton())
        Thread.sleep(7000)
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed())).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
    }
}