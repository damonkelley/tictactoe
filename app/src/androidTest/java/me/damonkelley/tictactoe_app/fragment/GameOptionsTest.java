package me.damonkelley.tictactoe_app.fragment;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe_app.R;
import me.damonkelley.tictactoe_app.activity.MainActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

public class GameOptionsTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);
    private GameOptions fragment;
    private ViewInteraction playerOneMarkerToggle;
    private ViewInteraction playerTwoMarkerToggle;

    @Before
    public void setUp() {
        fragment = (GameOptions) mActivityRule.getActivity()
                .getFragmentManager()
                .findFragmentById(R.id.options);

        playerOneMarkerToggle = onView(withId(R.id.player_one_marker));
        playerTwoMarkerToggle = onView(withId(R.id.player_two_marker));
    }

    @Test
    public void choosingHumanAndHuman() {
        choosePlayerType(R.id.player_one_type, "Human");
        choosePlayerType(R.id.player_two_type, "Human");

        assertEquals("human-vs-human", fragment.getPreset());
    }

    @Test
    public void choosingComputerAndHuman() {
        choosePlayerType(R.id.player_one_type, "Computer");
        choosePlayerType(R.id.player_two_type, "Human");

        assertEquals("computer-vs-human", fragment.getPreset());
    }

    @Test
    public void choosingHumanAndComputer() {
        choosePlayerType(R.id.player_one_type, "Human");
        choosePlayerType(R.id.player_two_type, "Computer");

        assertEquals("human-vs-computer", fragment.getPreset());
    }

    @Test
    public void togglingThePlayerOneMarkerTogglesThePlayerTwoMarker() {
        playerTwoMarkerToggle.check(matches(withText("O")));

        playerOneMarkerToggle.check(matches(withText("X")))
                .perform(click())
                .check(matches(withText("O")));

        playerTwoMarkerToggle.check(matches(withText("X")));
    }

    @Test
    public void togglingThePlayerTwoMarkerTogglesThePlayerOneMarker() {
        playerOneMarkerToggle.check(matches(withText("X")));

        playerTwoMarkerToggle.check(matches(withText("O")))
                .perform(click())
                .check(matches(withText("X")));

        playerOneMarkerToggle.check(matches(withText("O")));
    }

    @Test
    public void getFirstMarker() {
        assertEquals(Marker.X, fragment.getFirstMarker());
        playerOneMarkerToggle.perform(click());
        assertEquals(Marker.O, fragment.getFirstMarker());
    }

    private void choosePlayerType(int player_type, String option) {
        onView(withId(player_type)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(option))).perform(click());
    }
}
