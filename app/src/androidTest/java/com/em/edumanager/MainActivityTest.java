package com.em.edumanager;

import android.content.ContentResolver;
import android.content.Intent;
import android.provider.Settings;
import android.widget.GridView;
import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import androidx.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.HashMap;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule= new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity;


    @Before
    public void setUp() throws Exception {
        Intents.init();
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }





    @Test
    public void testAddStudentMenuItem() {
        // Launch the Activity
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);

        // Click on Add Student Menu Item
        onData(anything())
                .inAdapterView(withId(R.id.gridView1))
                .atPosition(0)
                .perform(click());

        //   Check if AddStudentInfoActivity
        onView(withId(R.id.addStudentInfoLayout))
                .check(matches(isDisplayed()));

    }

    @Test
    public void testMainStudentMenuItem() {
        // Launch the Activity
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);

        // Click on maintain Student Menu Item
        onData(anything())
                .inAdapterView(withId(R.id.gridView1))
                .atPosition(1)
                .perform(click());

        //   Check if showStudentInfoActivity
        onView(withId(R.id.showStudentInfoLayout))
                .check(matches(isDisplayed()));

    }


    @Test
    public void testHelpMenuItem() {
        // Launch the Activity
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);

        // Click on help  Menu Item
        onData(anything())
                .inAdapterView(withId(R.id.gridView1))
                .atPosition(5)
                .perform(click());

        //   check if show message
        onView(withText("User Guide")).check(matches(isDisplayed()));

    }

    @Test
    public void testExitMenuItem() {
        // Launch the Activity
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);

        // Click on exit  Menu Item
        onData(anything())
                .inAdapterView(withId(R.id.gridView1))
                .atPosition(6)
                .perform(click());

        //   check if show message
        onView(withText("Are you sure you want to exit?")).check(matches(isDisplayed()));

    }


}
