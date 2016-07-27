package me.damonkelley.tictactoe_app.loop;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe_app.turn.Turn;
import me.damonkelley.tictactoe_app.wrapper.UserInterfaceUpdater;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class LoopBuilderTest {
    private String log = "";

    @Test
    public void itBuildsALoop() {
        Loop loop = new LoopBuilder()
                .withFirstTurn(new LoggingTurn("first"))
                .withSecondTurn(new LoggingTurn("second"))
                .withFirstMarker(Marker.X)
                .withSecondMarker(Marker.O)
                .withGame(new Game(Marker.X))
                .build();

        loop.next(new Space(0, 0));
        loop.next(new Space(0, 1));

        assertEquals("first-initialized first-X second-O ", log);
    }

    @Test
    public void itSetsTheUpdater() {
        new LoopBuilder()
                .withFirstTurn(new LoggingTurn("first"))
                .withSecondTurn(new LoggingTurn("second"))
                .withFirstMarker(Marker.X)
                .withSecondMarker(Marker.O)
                .withUpdater(() -> {})
                .withGame(new Game(Marker.X));

        assertEquals("set-updater-X set-updater-O ", log);
    }

    private class LoggingTurn extends Turn {
        public String message;

        LoggingTurn(String message) {
            this.message = message;
        }

        @Override
        public void go(Space space) {
            this.loop.setNext(next);
            log += message+ "-" + marker + " ";
        }

        @Override
        public Turn setUpdater(UserInterfaceUpdater updater) {
            log += "set-updater-" + marker + " ";
            return this;
        }

        @Override
        public void initialize() {
            log += message + "-initialized ";
        }
    }
}
