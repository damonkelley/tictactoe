package me.damonkelley.tictactoe_app.loop;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe_app.loop.Loop;
import me.damonkelley.tictactoe_app.loop.LoopBuilder;
import me.damonkelley.tictactoe_app.turn.Turn;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class LoopBuilderTest {
    private String log = "";

    @Test
    public void itBuildsALoop() {
        Loop loop = new LoopBuilder()
                .withFirstTurn(new FakeTurn("first"))
                .withSecondTurn(new FakeTurn("second"))
                .withFirstMarker(Marker.X)
                .withSecondMarker(Marker.O)
                .withGame(new Game(Marker.X))
                .build();

        loop.next(new Space(0, 0));
        loop.next(new Space(0, 1));

        assertEquals("first-initialized first-X second-O ", log);
    }

    private class FakeTurn extends Turn {
        public String message;

        FakeTurn(String message) {
            this.message = message;
        }

        @Override
        public void go(Space space) {
            this.loop.setNext(next);
            log += message+ "-" + marker + " ";
        }

        @Override
        public void initialize() {
            log += message + "-initialized ";
        }
    }
}
