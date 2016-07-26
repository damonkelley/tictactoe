package me.damonkelley.tictactoe_app.turn;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe_app.loop.Loop;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultiPlayerHumanTurnTest {

    private FakeLoop loop;
    private Game game;
    private Turn turn;

    @Before
    public void setUp() throws Exception {
        loop = new FakeLoop();
        game = new Game(Marker.O);

        turn = new MultiPlayerHumanTurn()
                .setMarker(Marker.O)
                .setLoop(loop)
                .setGame(game);
    }

    @Test
    public void itMakesMoves() {
        turn.go(new Space(0, 0));
        assertEquals(Marker.O, game.getBoard().get(new Space(0, 0)));
    }

    @Test
    public void itSetsNextTurnButDoesNotAdvanceTheLoop() {
        turn.go(new Space(0, 0));
        assertEquals("set-next ", loop.log);

    }

    private class FakeLoop extends Loop {
        public String log = "";

        @Override
        public Loop setNext(Turn next) {
            log += "set-next ";
            return this;
        }

        @Override
        public void next(Space space) {
            throw new RuntimeException("should not call next");
        }
    }
}
