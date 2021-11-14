package com.denzhid.lab3_5

import android.content.pm.ActivityInfo
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.denzhid.lab3_5.activities.MainActivity
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @get:Rule
    val startingRule = ActivityScenarioRule(MainActivity::class.java)

    // ActivityAbout tests section
    @Test
    fun testAboutActivityFromFirst() {
        openAbout()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
    }

    @Test
    fun testAboutActivityFromSecond() {
        onView(withId(R.id.bnToSecond)).perform(click())
        openAbout()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))
    }

    @Test
    fun testAboutActivityFromThird() {
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))
    }

    // Backstack tests section
    @Test
    fun testBackstackFromFirst() {
        Espresso.pressBackUnconditionally()
        assertTrue(startingRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun testBackstackFromSecond() {
        onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.pressBack()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        assertTrue(startingRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun testBackstackFromThird() {
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        Espresso.pressBack()
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        Espresso.pressBack()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        assertTrue(startingRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun testBackstackFromFirstWithActivityAbout() {
        openAbout()
        Espresso.pressBack()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        assertTrue(startingRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun testBackstackFromSecondWithActivityAbout() {
        onView(withId(R.id.bnToSecond)).perform(click())
        openAbout()
        Espresso.pressBack()
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        Espresso.pressBack()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        assertTrue(startingRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun testBackstackFromThirdWithActivityAbout() {
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        Espresso.pressBack()
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        Espresso.pressBack()
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        Espresso.pressBack()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        assertTrue(startingRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun testBackstackForCompleteNavigationInApp() {
        openAbout()
        Espresso.pressBack()
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        Espresso.pressBack()
        onView(withId(R.id.bnToFirst)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        openAbout()
        Espresso.pressBack()
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToFirst)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToFirst)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        Espresso.pressBack()
        Espresso.pressBack()
        Espresso.pressBack()
        Espresso.pressBackUnconditionally()
        assertTrue(startingRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    // Back test section
    @Test
    fun testBackFromFirst() {
        Espresso.pressBackUnconditionally()
        assertTrue(startingRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun testBackFromSecond() {
        onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.pressBack()
        Espresso.pressBackUnconditionally()
        assertTrue(startingRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun testBackFromThird() {
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        Espresso.pressBack()
        Espresso.pressBack()
        Espresso.pressBackUnconditionally()
        assertTrue(startingRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun testBackFromFirstWithActivityAbout() {
        openAbout()
        Espresso.pressBack()
        Espresso.pressBackUnconditionally()
        assertTrue(startingRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun testBackFromSecondWithActivityAbout() {
        onView(withId(R.id.bnToSecond)).perform(click())
        openAbout()
        Espresso.pressBack()
        Espresso.pressBack()
        Espresso.pressBackUnconditionally()
        assertTrue(startingRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun testBackFromThirdWithActivityAbout() {
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        Espresso.pressBack()
        Espresso.pressBack()
        Espresso.pressBack()
        Espresso.pressBackUnconditionally()
        assertTrue(startingRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    // Content test
    @Test
    fun testContentAtFirst() {
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(doesNotExist())
        onView(withId(R.id.bnToThird)).check(doesNotExist())
    }

    @Test
    fun testContentAtSecond() {
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(doesNotExist())
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(matches(isDisplayed()))
    }

    @Test
    fun testContentAtThird() {
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(doesNotExist())
    }

    @Test
    fun testContentAtActivityAbout() {
        openAbout()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))
    }

    // Rotates test section
    @Test
    fun testRotatesFirst() {
        startingRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(doesNotExist())
        onView(withId(R.id.bnToThird)).check(doesNotExist())

        startingRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        Thread.sleep(1000)
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(doesNotExist())
        onView(withId(R.id.bnToThird)).check(doesNotExist())
    }

    @Test
    fun testRotatesSecond() {
        onView(withId(R.id.bnToSecond)).perform(click())

        startingRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(doesNotExist())
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(matches(isDisplayed()))

        startingRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        Thread.sleep(1000)
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(doesNotExist())
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(matches(isDisplayed()))
    }

    @Test
    fun testRotatesThird() {
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())

        startingRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(doesNotExist())

        startingRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        Thread.sleep(1000)
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(doesNotExist())
    }

    @Test
    fun testRotatesActivityAbout() {
        openAbout()

        startingRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))

        startingRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        Thread.sleep(1000)
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))
    }

    @Test
    fun testBackstackAfterRotateFirst() {
        startingRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)
        Espresso.pressBackUnconditionally()
        assertTrue(startingRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun testBackstackAfterRotateSecond() {
        onView(withId(R.id.bnToSecond)).perform(click())
        startingRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)
        Espresso.pressBack()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        assertTrue(startingRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun testBackstackAfterRotateThird() {
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        startingRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)
        Espresso.pressBack()
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        Espresso.pressBack()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        assertTrue(startingRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun testBackstackAfterRotateActivityAbout() {
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        startingRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)
        Espresso.pressBack()
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        Espresso.pressBack()
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        Espresso.pressBack()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        assertTrue(startingRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    // Up navigation test section
    @Test
    fun testUpNavigationFromFirst() {
        try {
            onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
            assert(false)
        } catch (NoActivityResumedException: Exception) {
            assert(true)
        }
    }

    @Test
    fun testUpNavigationFromSecond() {
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(doesNotExist())
        onView(withId(R.id.bnToThird)).check(doesNotExist())
        try {
            onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
            assert(false)
        } catch (NoActivityResumedException: Exception) {
            assert(true)
        }
    }

    @Test
    fun testUpNavigationFromThird() {
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(doesNotExist())
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(matches(isDisplayed()))
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(doesNotExist())
        onView(withId(R.id.bnToThird)).check(doesNotExist())
        try {
            onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
            assert(false)
        } catch (NoActivityResumedException: Exception) {
            assert(true)
        }
    }

    @Test
    fun testUpNavigationFromFirstWithAbout() {
        openAbout()
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(doesNotExist())
        onView(withId(R.id.bnToThird)).check(doesNotExist())
        try {
            onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
            assert(false)
        } catch (NoActivityResumedException: Exception) {
            assert(true)
        }
    }

    @Test
    fun testUpNavigationFromSecondWithAbout() {
        onView(withId(R.id.bnToSecond)).perform(click())
        openAbout()
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(doesNotExist())
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(matches(isDisplayed()))
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(doesNotExist())
        onView(withId(R.id.bnToThird)).check(doesNotExist())
        try {
            onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
            assert(false)
        } catch (NoActivityResumedException: Exception) {
            assert(true)
        }
    }

    @Test
    fun testUpNavigationFromThirdWithAbout() {
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(doesNotExist())
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(doesNotExist())
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(matches(isDisplayed()))
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(doesNotExist())
        onView(withId(R.id.bnToThird)).check(doesNotExist())
        try {
            onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
            assert(false)
        } catch (NoActivityResumedException: Exception) {
            assert(true)
        }
    }

    @Test
    fun testUpNavigationComplete() {
        openAbout()
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withId(R.id.bnToFirst)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        openAbout()
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToFirst)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToFirst)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        try {
            onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
            assert(false)
        } catch (NoActivityResumedException: Exception) {
            assert(true)
        }
    }
}