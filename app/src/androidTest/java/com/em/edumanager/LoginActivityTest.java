package com.em.edumanager;

import android.content.Intent;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.*;

public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule= new ActivityTestRule<>(LoginActivity.class,true,false);

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
        onView(withId(R.id.fullName)).check(matches(isDisplayed()));
        onView(withId(R.id.loginPassword)).check(matches(isDisplayed()));
        onView(withId(R.id.loginButton)).check(matches(isDisplayed()));
        onView(withId(R.id.registerButton)).check(matches(isDisplayed()));
        onView(withId(R.id.infoCheckedBox)).check(matches(isDisplayed()));
        onView(withId(R.id.contactUs)).check(matches(isDisplayed()));

    }


    @Test
    public void testLoginButton() {
        Intent intent=new Intent();
        activityTestRule.launchActivity(intent);
        onView(withId(R.id.fullName)).perform(typeText("tom.ding"),closeSoftKeyboard());
        onView(withId(R.id.loginPassword)).perform(typeText("123"),closeSoftKeyboard());
        //Click the loginButton
        onView(withId(R.id.loginButton))
                .check(matches(isDisplayed()))
                .check(matches(isEnabled()))
                .perform(click());

        // matching input fields
        onView(withId(R.id.fullName)).check(matches(withText("tom.ding")));
        onView(withId(R.id.loginPassword)).check(matches(withText("123")));

    }

    @Test
    public void testRegisterButton() {
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);
        onView(withId(R.id.registerButton))
                .check(matches(isDisplayed()))
                .check(matches(isEnabled()))
                .perform(click());

        // verifying if the app navigates to register activity
        intended(hasComponent(RegisterActivity.class.getName()));
        System.out.println("Register button click test passed!");

    }

    @Test
    public void testCallContactUsButton() {
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);
        onView(withId(R.id.contactUs))
                .check(matches(isDisplayed()))
                .check(matches(isEnabled()))
                .perform(click());

        // verifying if the app navigates to contact us activity
        intended(hasAction(Intent.ACTION_DIAL));
        System.out.println("Contact us button click test passed and navigated to DialerActivity!");

    }


}
