package com.em.edumanager;

import android.content.Intent;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.rule.ActivityTestRule;
import com.em.edumanager.bean.CommonData;
import com.em.edumanager.bean.StudentGrade;
import com.em.edumanager.bean.StudentInfo;
import com.em.edumanager.bean.UserInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class ModifyPasswordActivityTest {


    @Rule
    public ActivityTestRule<ModifyPasswordActivity> activityTestRule= new ActivityTestRule<>(ModifyPasswordActivity.class,true,false);


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
        UserInfo user = new UserInfo();
        user.setFirstname("test");
        user.setLastname("android");
        user.setPassword("123");
        CommonData.userLogin=user;
        activityTestRule.launchActivity(intent);

        onView(withId(R.id.oldPasswordEdit)).check(matches(isDisplayed()));
        onView(withId(R.id.newPasswordEdit)).check(matches(isDisplayed()));
        onView(withId(R.id.confirmPasswordEdit)).check(matches(isDisplayed()));
        onView(withId(R.id.butModify)).check(matches(isDisplayed()));
        onView(withId(R.id.butClear)).check(matches(isDisplayed()));

    }

    @Test
    public void testUpdatePassword() {

        Intent intent = new Intent();
        UserInfo user = new UserInfo();
        user.setFirstname("test");
        user.setLastname("android");
        user.setPassword("123");
        CommonData.userLogin=user;
        activityTestRule.launchActivity(intent);

        onView(withId(R.id.oldPasswordEdit)).perform(typeText("123"),closeSoftKeyboard());
        onView(withId(R.id.newPasswordEdit)).perform(typeText("321"), closeSoftKeyboard());
        onView(withId(R.id.confirmPasswordEdit)).perform(typeText("321"), closeSoftKeyboard());
        onView(withId(R.id.butModify)).perform(click());

        // matching input fields
        onView(withId(R.id.oldPasswordEdit)).check(matches(withText("123")));
        onView(withId(R.id.newPasswordEdit)).check(matches(withText("321")));
        onView(withId(R.id.confirmPasswordEdit)).check(matches(withText("321")));

    }

}
