package me.damonkelley.tictactoe_app;

import android.preference.Preference;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.PreferenceMatchers.withKey;
import static android.support.test.espresso.matcher.PreferenceMatchers.withSummaryText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MenuTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void theMenuIsDisplayed() {
        onView(withId(R.id.settings)).check(matches(isDisplayed()));
    }

    @Test
    public void aHumanVsHumanGameCanBeConfigured() {
        onData(allOf(is(instanceOf(Preference.class)), withKey("game_type")))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withText("Human vs. Human")).perform(click());

        onData(allOf(is(instanceOf(Preference.class)), withKey("game_type"), withSummaryText("Human vs. Human")))
                .check(matches(isDisplayed()));
    }

    @Test
    public void aHumanVsComputerGameCanBeConfigured() {
        onData(allOf(is(instanceOf(Preference.class)), withKey("game_type")))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withText("Human vs. Computer")).perform(click());

        onData(allOf(is(instanceOf(Preference.class)), withKey("game_type"), withSummaryText("Human vs. Computer")))
                .check(matches(isDisplayed()));
    }
}
