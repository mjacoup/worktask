package com.example.worktask.Views;

import android.provider.ContactsContract;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.worktask.R;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
@RunWith(AndroidJUnit4.class)
public class CalculatorTest extends TestCase {
    private String stringToBetyped;
    @Rule
    public ActivityScenarioRule<Calculator> activityRule = new ActivityScenarioRule<>(Calculator.class);
    @Before
    public void initValidString() {
        // Specify a valid string.
        stringToBetyped = "5";
    }
    @Test
    public void  test_User_inputs()
    {
       //activityRule.getScenario().recreate();
        onView(withId(R.id.Plus)).perform(click());
        onView(withId(R.id.value_enter)).perform(typeText("5"));
       Espresso.closeSoftKeyboard();
        onView(withId(R.id.equal)).perform(click());
        onView(withId(R.id.result_id)).check(matches(withText("5.0")));
        onView(withId(R.id.miltiply)).perform(click());
        onView(withId(R.id.value_enter)).perform(typeText("5103"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.equal)).perform(click());
        onView(withId(R.id.result_id)).check(matches(withText("25515.0")));
    }
    @Test
    public void  test_undo_Button()
    {
        onView(withId(R.id.Plus)).perform(click());
        onView(withId(R.id.value_enter)).perform(typeText("5"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.equal)).perform(click());
        onView(withId(R.id.result_id)).check(matches(withText("5.0")));
        onView(withId(R.id.miltiply)).perform(click());
        onView(withId(R.id.value_enter)).perform(typeText("5103"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.equal)).perform(click());
        onView(withId(R.id.result_id)).check(matches(withText("25515.0")));
        onView(withId(R.id.undo)).perform(click());
        onView(withId(R.id.result_id)).check(matches(withText("5.0")));




    }
    @Test
    public void  test_redo_Button()
    {
        onView(withId(R.id.Plus)).perform(click());
        onView(withId(R.id.value_enter)).perform(typeText("5"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.equal)).perform(click());
        onView(withId(R.id.result_id)).check(matches(withText("5.0")));
        onView(withId(R.id.miltiply)).perform(click());
        onView(withId(R.id.value_enter)).perform(typeText("5103"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.equal)).perform(click());
        onView(withId(R.id.result_id)).check(matches(withText("25515.0")));
        onView(withId(R.id.undo)).perform(click());
        onView(withId(R.id.undo)).perform(click());
        onView(withId(R.id.reudo)).perform(click());
        onView(withId(R.id.result_id)).check(matches(withText("5.0")));
    }


}