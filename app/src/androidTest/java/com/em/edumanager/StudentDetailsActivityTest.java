package com.em.edumanager;

import android.content.Intent;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;
import com.em.edumanager.bean.StudentInfo;
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

public class StudentDetailsActivityTest {

    @Rule
    public ActivityTestRule<StudentDetailsActivity> activityTestRule= new ActivityTestRule<>(StudentDetailsActivity.class,true,false);


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
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setStudentID("1234567890");
        studentInfo.setFirstname("John");
        studentInfo.setLastname("Doe");
        studentInfo.setAge("18");
        studentInfo.setGender("Male");
        studentInfo.setMajor("Computer Science");
        intent.putExtra("studentInfo", studentInfo);
        activityTestRule.launchActivity(intent);
        onView(withId(R.id.updateStudentID)).check(matches(isDisplayed()));
        onView(withId(R.id.firstNameTextView)).check(matches(isDisplayed()));
        onView(withId(R.id.lastNameTextView)).check(matches(isDisplayed()));
        onView(withId(R.id.updateAge)).check(matches(isDisplayed()));
        onView(withId(R.id.updateRemark)).check(matches(isDisplayed()));
        onView(withId(R.id.radioMale)).check(matches(isDisplayed()));
        onView(withId(R.id.radioFeMale)).check(matches(isDisplayed()));
        onView(withId(R.id.saveButton)).check(matches(isDisplayed()));
        onView(withId(R.id.deleteButton)).check(matches(isDisplayed()));

    }


    @Test
    public void testUpdateStudentInfo() {

        Intent intent = new Intent();
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setStudentID("1234567890");
        studentInfo.setFirstname("John");
        studentInfo.setLastname("Doe");
        studentInfo.setAge("18");
        studentInfo.setGender("Male");
        studentInfo.setMajor("Computer Science");
        intent.putExtra("studentInfo", studentInfo);
        activityTestRule.launchActivity(intent);
        onView(withId(R.id.firstNameTextView)).perform(typeText("John"), closeSoftKeyboard());
        onView(withId(R.id.lastNameTextView)).perform(typeText("Doe"), closeSoftKeyboard());
        onView(withId(R.id.updateAge)).perform(typeText("20"), closeSoftKeyboard());
        onView(withId(R.id.updateRemark)).perform(typeText("Good student"), closeSoftKeyboard());
        onView(withId(R.id.radioMale)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.saveButton)).perform(click());

        onView(withId(R.id.firstNameTextView)).check(matches(withText("John")));
        onView(withId(R.id.lastNameTextView)).check(matches(withText("Doe")));
        onView(withId(R.id.updateAge)).check(matches(withText("20")));
        onView(withId(R.id.updateRemark)).check(matches(withText("Good student")));

    }

    @Test
    public void testDeleteStudentInfo() {

        Intent intent = new Intent();
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setStudentID("1234567890");
        studentInfo.setFirstname("John");
        studentInfo.setLastname("Doe");
        studentInfo.setAge("18");
        studentInfo.setGender("Male");
        studentInfo.setMajor("Computer Science");
        intent.putExtra("studentInfo", studentInfo);
        activityTestRule.launchActivity(intent);
        onView(withId(R.id.deleteButton)).perform(click());

        //   check if show message
        onView(withText("Are you sure you want to delete this student information?")).check(matches(isDisplayed()));
    }
}
