package me.damonkelley.tictactoe_app.fragment;

import android.support.test.rule.ActivityTestRule;
import me.damonkelley.tictactoe_app.MainActivity;
import me.damonkelley.tictactoe_app.R;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

public class GameOptionsTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);
    private GameOptions fragment;

    @Before
    public void setUp() {
        fragment = (GameOptions) mActivityRule.getActivity()
                .getFragmentManager()
                .findFragmentById(R.id.options);
    }

    @Test
    public void choosingHumanAndHuman() {
        choosePlayerType(R.id.player_one_type, "Human");
        choosePlayerType(R.id.player_two_type, "Human");

        assertEquals("Human", fragment.getPlayerOneType());
        assertEquals("Human", fragment.getPlayerTwoType());
    }

    @Test
    public void choosingComputerAndHuman() {
        choosePlayerType(R.id.player_one_type, "Computer");
        choosePlayerType(R.id.player_two_type, "Human");

        assertEquals("Computer", fragment.getPlayerOneType());
        assertEquals("Human", fragment.getPlayerTwoType());
    }

    @Test
    public void choosingHumanAndComputer() {
        choosePlayerType(R.id.player_one_type, "Human");
        choosePlayerType(R.id.player_two_type, "Computer");

        assertEquals("Human", fragment.getPlayerOneType());
        assertEquals("Computer", fragment.getPlayerTwoType());
    }

    private void choosePlayerType(int player_type, String option) {
        onView(withId(player_type)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(option))).perform(click());
    }
}
