package me.damonkelley.tictactoe_app;

import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
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
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        PreferenceManager.getDefaultSharedPreferences(mActivityRule.getActivity())
                .edit()
                .clear()
                .commit();
    }

    @Test
    public void startGameShowsTheBoard() {
        onView(withId(R.id.start_button)).perform(click());
        onView(withId(R.id.game_message)).check(matches(withText("Good luck!")));
    }

    @Test
    public void clickingOnASpacePlacesTheMarker() {
        onView(withId(R.id.start_button)).perform(click());
        clickAndCheck(0, "X");
        clickAndCheck(1, "O");
    }

    @Test
    public void aMessageIsDisplayedWhenXWins() {
        onView(withId(R.id.start_button)).perform(click());

        clickSpace(0);
        clickSpace(2);
        clickSpace(6);
        clickSpace(4);
        clickSpace(3);

        onView(withId(R.id.game_message)).check(matches(withText("X wins!")));
    }

    @Test
    public void aMessageIsDisplayedWhenOWins() {
        onView(withId(R.id.start_button)).perform(click());

        clickSpace(0);
        clickSpace(1);
        clickSpace(6);
        clickSpace(4);
        clickSpace(2);
        clickSpace(7);

        onView(withId(R.id.game_message)).check(matches(withText("O wins!")));
    }

    @Test
    public void aMessageIsDisplayedWhenItIsADraw() {
        onView(withId(R.id.start_button)).perform(click());

        clickSpace(0);
        clickSpace(1);
        clickSpace(6);
        clickSpace(3);
        clickSpace(2);
        clickSpace(4);
        clickSpace(5);
        clickSpace(8);
        clickSpace(7);

        onView(withId(R.id.game_message)).check(matches(withText("Draw")));
    }

    private void clickSpace(int position) {
        onData(anything())
                .inAdapterView(withId(R.id.game))
                .atPosition(position)
                .perform(click());

    }

    private void clickAndCheck(int position, String marker) {
        onData(anything())
                .inAdapterView(withId(R.id.game))
                .atPosition(position)
                .perform(click())
                .check(matches(withText(marker)));
    }

    @Test
    public void playAHumanVsComputerGame() {
        onData(allOf(is(instanceOf(Preference.class)), withKey("game_type")))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withText("Human vs. Computer")).perform(click());

        onData(allOf(is(instanceOf(Preference.class)), withKey("game_type"), withSummaryText("Human vs. Computer")))
                .check(matches(isDisplayed()));

        onView(withId(R.id.start_button)).perform(click());

        clickSpace(0);
        clickSpace(3);
        clickSpace(1);

        onView(withId(R.id.game_message)).check(matches(withText("O wins!")));
    }
}

