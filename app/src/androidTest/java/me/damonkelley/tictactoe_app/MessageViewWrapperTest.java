package me.damonkelley.tictactoe_app;

import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;
import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Space;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageViewWrapperTest {

    private Game game;
    private TextView view;

    @Rule
    public ActivityTestRule<GameActivity> mActivityRule = new ActivityTestRule(GameActivity.class);

    @Before
    public void setUp() throws Exception {
        game = new Game(Marker.X);
        view = new TextView(mActivityRule.getActivity());
    }

    @Test
    public void theMessageIsDraw() {
        makeDraw();

        MessageViewWrapper wrapper = new MessageViewWrapper(game, view);
        wrapper.update();

        assertEquals("Draw", view.getText());
    }

    @Test
    public void theMessageIsXWins() {
        makeXWin();

        MessageViewWrapper wrapper = new MessageViewWrapper(game, view);
        wrapper.update();

        assertEquals("X wins!", view.getText());
    }

    @Test
    public void theMessageIsOWins() {
        makeOWin();

        MessageViewWrapper wrapper = new MessageViewWrapper(game, view);
        wrapper.update();

        assertEquals("O wins!", view.getText());
    }

    private void makeOWin() {
        game.move(new Space(2, 2), Marker.X);
        game.move(new Space(1, 0), Marker.O);
        game.move(new Space(0, 1), Marker.X);
        game.move(new Space(1, 1), Marker.O);
        game.move(new Space(0, 2), Marker.X);
        game.move(new Space(1, 2), Marker.O);
    }

    private void makeXWin() {
        game.move(new Space(0, 0), Marker.X);
        game.move(new Space(1, 0), Marker.O);
        game.move(new Space(0, 1), Marker.X);
        game.move(new Space(1, 1), Marker.O);
        game.move(new Space(0, 2), Marker.X);
    }

    private void makeDraw() {
        game.move(new Space(0, 0), Marker.X);
        game.move(new Space(1, 0), Marker.O);
        game.move(new Space(2, 0), Marker.X);
        game.move(new Space(0, 2), Marker.O);
        game.move(new Space(1, 1), Marker.X);
        game.move(new Space(2, 2), Marker.O);
        game.move(new Space(1, 2), Marker.X);
        game.move(new Space(0, 1), Marker.O);
        game.move(new Space(2, 1), Marker.X);

    }
}
