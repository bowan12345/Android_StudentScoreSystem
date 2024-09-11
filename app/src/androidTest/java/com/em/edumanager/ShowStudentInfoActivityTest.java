package com.em.edumanager;

import android.content.Intent;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
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
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.anything;

public class ShowStudentInfoActivityTest {

    @Rule
    public ActivityTestRule<ShowStudentInfoActivity> activityTestRule= new ActivityTestRule<>(ShowStudentInfoActivity.class,true,false);


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
        onView(withId(R.id.showAllButton)).check(matches(isDisplayed()));
        onView(withId(R.id.showQuery)).check(matches(isDisplayed()));
        onView(withId(R.id.studentIDQuery)).check(matches(isDisplayed()));

    }


    @Test
    public void testStudentIdQueryButton(){
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);
        onView(withId(R.id.studentIDQuery)).perform(typeText("1234567890"), closeSoftKeyboard());
        onView(withId(R.id.showQuery)).perform(click());

        //matching input fields
        onView(withId(R.id.studentIDQuery)).check(ViewAssertions.matches(withText("1234567890")));
    }


    @Test
    public void testAllQueryButton(){
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);
        onView(withId(R.id.showAllButton)).perform(click());

        //matching input fields
        onData(anything())
                .inAdapterView(withId(R.id.studentListView))
                .atPosition(0)
                .check(matches(isDisplayed()));
    }



}
