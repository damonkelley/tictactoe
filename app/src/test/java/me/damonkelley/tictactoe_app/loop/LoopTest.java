package me.damonkelley.tictactoe_app.loop;

import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe_app.turn.NullTurn;
import me.damonkelley.tictactoe_app.turn.Turn;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoopTest {

    @Test
    public void itDispatchesToTheNextTurn() {
        FakeTurn turn = new FakeTurn();
        Loop loop = new Loop()
                .setNext(turn);

        loop.next(new Space(0, 1));

        assertEquals(true, turn.called);
    }

    private class FakeTurn extends Turn {
        public boolean called;

        public Turn next = new NullTurn();

        @Override
        public void go(Space space) {
            this.loop.setNext(next);
            called = true;
        }
    }
}
