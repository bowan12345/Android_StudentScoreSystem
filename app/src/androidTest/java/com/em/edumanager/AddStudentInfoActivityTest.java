package com.em.edumanager;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

public class AddStudentInfoActivityTest {

    @Rule
    public ActivityScenarioRule<AddStudentInfoActivity> activityRule = new ActivityScenarioRule<>(AddStudentInfoActivity.class);

    AddStudentInfoActivity activity;

    @Before
    public void setUp() throws Exception {


    }

    @After
    public void tearDown() throws Exception {
        activity = null;
    }


    @Test
    public void testAllViewsAreDisplayed() {
        onView(withId(R.id.studentIDEdit)).check(matches(isDisplayed()));
        onView(withId(R.id.firstNameEdit)).check(matches(isDisplayed()));
        onView(withId(R.id.lastNameEdit)).check(matches(isDisplayed()));
        onView(withId(R.id.addageedit)).check(matches(isDisplayed()));
        onView(withId(R.id.addRadioMale)).check(matches(isDisplayed()));
        onView(withId(R.id.addRadioFemale)).check(matches(isDisplayed()));
        onView(withId(R.id.addbutton)).check(matches(isDisplayed()));
    }


    @Test
    public void testAddStudent() {
        // adding student information
        onView(withId(R.id.studentIDEdit)).perform(typeText("123456789"),closeSoftKeyboard());
        onView(withId(R.id.firstNameEdit)).perform(typeText("John"), closeSoftKeyboard());
        onView(withId(R.id.lastNameEdit)).perform(typeText("Doe"), closeSoftKeyboard());
        onView(withId(R.id.addageedit)).perform(typeText("18"), closeSoftKeyboard());
        onView(withId(R.id.addRadioFemale)).perform(click());
        onView(withId(R.id.addbutton)).perform(click());

        // matching input fields
        onView(withId(R.id.studentIDEdit)).check(matches(withText("123456789")));
        onView(withId(R.id.firstNameEdit)).check(matches(withText("John")));
        onView(withId(R.id.lastNameEdit)).check(matches(withText("Doe")));
        onView(withId(R.id.addageedit)).check(matches(withText("18")));
        onView(withId(R.id.addRadioFemale)).check(matches(isChecked()));

    }
}
