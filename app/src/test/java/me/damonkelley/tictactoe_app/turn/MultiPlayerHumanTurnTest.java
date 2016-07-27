package me.damonkelley.tictactoe_app.turn;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe_app.loop.Loop;
import me.damonkelley.tictactoe_app.wrapper.UserInterfaceUpdater;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultiPlayerHumanTurnTest {

    private FakeLoop loop;
    private Game game;
    private Turn turn;
    private FakeUpdater updater;

    @Before
    public void setUp() throws Exception {
        loop = new FakeLoop();
        game = new Game(Marker.O);
        updater = new FakeUpdater();

        turn = new MultiPlayerHumanTurn()
                .setMarker(Marker.O)
                .setLoop(loop)
                .setUpdater(updater)
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

    @Test
    public void itUpdatesTheUserInterface() {
        turn.go(new Space(0, 0));
        assertEquals("updated ", updater.log);
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

    private class FakeUpdater implements UserInterfaceUpdater {
        public String log = "";

        @Override
        public void update() {
            log += "updated ";
        }
    }
}
