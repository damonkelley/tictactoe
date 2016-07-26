package me.damonkelley.tictactoe_app;

import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe_app.turn.NullTurn;
import me.damonkelley.tictactoe_app.turn.Turn;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoopTest {
    private String log = "";

    @Test
    public void itDispatchesToTheNextTurn() {
        FakeTurn turn = new FakeTurn("called");
        Loop loop = new Loop()
                .setNext(turn);

        loop.next(new Space(0, 1));

        assertEquals("called ", log);
    }

    private class FakeTurn extends Turn {
        public String message;

        public Turn next = new NullTurn();

        FakeTurn(String message) {
            this.message = message;
        }

        @Override
        public void go(Space space) {
            this.loop.setNext(next);
            log += message + " ";
        }
    }
}
