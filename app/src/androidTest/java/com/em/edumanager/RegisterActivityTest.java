package com.em.edumanager;

import android.content.Intent;
import android.os.SystemClock;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<RegisterActivity> activityTestRule= new ActivityTestRule<>(RegisterActivity.class);



    @Before
    public void setUp() throws Exception {
        Intents.init();
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }


    @Test
    public void testAllViewsAreDisplayed() {
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);
        onView(withId(R.id.firstnameText)).check(matches(isDisplayed()));
        onView(withId(R.id.lastnameText)).check(matches(isDisplayed()));
        onView(withId(R.id.emailText)).check(matches(isDisplayed()));
        onView(withId(R.id.passwordText)).check(matches(isDisplayed()));
        onView(withId(R.id.rePasswordText)).check(matches(isDisplayed()));
        onView(withId(R.id.registerSave)).check(matches(isDisplayed()));

    }

    @Test
    public void testRegisterButton() {
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);
        onView(withId(R.id.firstnameText)).perform(typeText("Emma"), closeSoftKeyboard());
        onView(withId(R.id.lastnameText)).perform(typeText("Johnson"), closeSoftKeyboard());
        onView(withId(R.id.emailText)).perform(typeText("emma@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.passwordText)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.rePasswordText)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.registerSave))
                .check(matches(isDisplayed()))
                .check(matches(isEnabled()))
                .perform(click());

        onView(withId(R.id.firstnameText)).check(matches(withText("Emma")));
        onView(withId(R.id.lastnameText)).check(matches(withText("Johnson")));
        onView(withId(R.id.emailText)).check(matches(withText("emma@gmail.com")));
        onView(withId(R.id.passwordText)).check(matches(withText("123456")));
        onView(withId(R.id.rePasswordText)).check(matches(withText("123456")));

    }
}
