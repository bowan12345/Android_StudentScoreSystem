package com.em.edumanager;

import android.content.Intent;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;
import com.em.edumanager.bean.StudentGrade;
import com.em.edumanager.bean.StudentInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

public class GradeDetailsActivityTest {

    @Rule
    public ActivityTestRule<GradeDetailsActivity> activityTestRule= new ActivityTestRule<>(GradeDetailsActivity.class,true,false);


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
        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setStudentId("1234567890");
        studentGrade.setFirstname("John");
        studentGrade.setLastname("Doe");
        studentGrade.setAndroid("100");
        studentGrade.setJava("100");
        studentGrade.setHtml("100");
        intent.putExtra("studentGrade", studentGrade);
        activityTestRule.launchActivity(intent);
        onView(withId(R.id.gradeStudentIDText)).check(matches(isDisplayed()));
        onView(withId(R.id.gradeFirstnameText)).check(matches(isDisplayed()));
        onView(withId(R.id.gradeLastnameText)).check(matches(isDisplayed()));
        onView(withId(R.id.gradeAndroidText)).check(matches(isDisplayed()));
        onView(withId(R.id.gradeJavaText)).check(matches(isDisplayed()));
        onView(withId(R.id.gradeHtmlText)).check(matches(isDisplayed()));
        onView(withId(R.id.gradeSaveButton)).check(matches(isDisplayed()));
        onView(withId(R.id.gradeDeleteButton)).check(matches(isDisplayed()));

    }


    @Test
    public void testUpdateStudentGrade() {

        Intent intent = new Intent();
        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setStudentId("1234567890");
        studentGrade.setFirstname("John");
        studentGrade.setLastname("Doe");
        studentGrade.setAndroid("100");
        studentGrade.setJava("100");
        studentGrade.setHtml("100");
        intent.putExtra("studentGrade", studentGrade);
        activityTestRule.launchActivity(intent);
        onView(withId(R.id.gradeAndroidText)).perform(clearText(),typeText("99"), closeSoftKeyboard());
        onView(withId(R.id.gradeJavaText)).perform(clearText(),typeText("99"), closeSoftKeyboard());
        onView(withId(R.id.gradeHtmlText)).perform(clearText(),typeText("99"), closeSoftKeyboard());
        onView(withId(R.id.gradeSaveButton)).perform(click());

        onView(withId(R.id.gradeAndroidText)).check(matches(withText("99")));
        onView(withId(R.id.gradeJavaText)).check(matches(withText("99")));
        onView(withId(R.id.gradeHtmlText)).check(matches(withText("99")));

    }

    @Test
    public void testDeleteStudentGrade() {

        Intent intent = new Intent();
        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setStudentId("1234567890");
        studentGrade.setFirstname("John");
        studentGrade.setLastname("Doe");
        studentGrade.setAndroid("100");
        studentGrade.setJava("100");
        studentGrade.setHtml("100");
        intent.putExtra("studentGrade", studentGrade);
        activityTestRule.launchActivity(intent);
        onView(withId(R.id.gradeDeleteButton)).perform(click());

        //   check if show message
        onView(withText("Are you sure you want to delete this student grade?")).check(matches(isDisplayed()));
    }
}
