package com.em.edumanager;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

public class AddStudentGradeActivityTest {

    @Rule
    public ActivityScenarioRule<AddStudentGradeActivity> activityRule = new ActivityScenarioRule<>(AddStudentGradeActivity.class);

    AddStudentGradeActivity activity;

    @Before
    public void setUp() throws Exception {


    }

    @After
    public void tearDown() throws Exception {
        activity = null;
    }


    @Test
    public void testAllViewsAreDisplayed() {
        onView(withId(R.id.studentIDGradeEdit)).check(matches(isDisplayed()));
        onView(withId(R.id.androidGradeEdit)).check(matches(isDisplayed()));
        onView(withId(R.id.javaGradeEdit)).check(matches(isDisplayed()));
        onView(withId(R.id.htmlGradeEdit)).check(matches(isDisplayed()));
        onView(withId(R.id.studentIDText)).check(matches(isDisplayed()));
        onView(withId(R.id.firstnameGradeText)).check(matches(isDisplayed()));
        onView(withId(R.id.lastnameGradeText)).check(matches(isDisplayed()));
        onView(withId(R.id.butQueryButton)).check(matches(isDisplayed()));
        onView(withId(R.id.butAddButton)).check(matches(isDisplayed()));
        onView(withId(R.id.butClearButton)).check(matches(isDisplayed()));
    }


    @Test
    public void AddGradeAction() {
        // adding student information
        onView(withId(R.id.androidGradeEdit)).perform(typeText("90"), closeSoftKeyboard());
        onView(withId(R.id.javaGradeEdit)).perform(typeText("100"), closeSoftKeyboard());
        onView(withId(R.id.htmlGradeEdit)).perform(typeText("95"), closeSoftKeyboard());
        onView(withId(R.id.butAddButton)).perform(click());

        // matching input fields
        onView(withId(R.id.androidGradeEdit)).check(matches(withText("90")));
        onView(withId(R.id.javaGradeEdit)).check(matches(withText("100")));
        onView(withId(R.id.htmlGradeEdit)).check(matches(withText("95")));

    }
}
