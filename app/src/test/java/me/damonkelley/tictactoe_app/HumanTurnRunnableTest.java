package me.damonkelley.tictactoe_app;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Space;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class HumanTurnRunnableTest {
    @Test
    public void itMakesAMove() {
        Game game = new Game(Marker.X);

        new HumanTurnRunnable(new Space(0, 1), Marker.X, game).run();
        assertEquals(Marker.X, game.getBoard().get(new Space(0, 1)));

        game = new Game(Marker.X);
        new HumanTurnRunnable(new Space(1, 1), Marker.X, game).run();
        assertEquals(Marker.X, game.getBoard().get(new Space(1, 1)));
    }
}
