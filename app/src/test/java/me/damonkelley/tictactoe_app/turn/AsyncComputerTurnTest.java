package me.damonkelley.tictactoe_app.turn;

import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe_app.loop.Loop;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AsyncComputerTurnTest {

    @Test
    public void itAdvancesTheLoopDuringInitialization() {
        FakeLoop loop = new FakeLoop();
        Turn turn = new AsyncComputerTurn();

        loop.setNext(turn);
        turn.setLoop(loop);

        turn.initialize();
        assertEquals(true, loop.calledNext);
    }

    private class FakeLoop extends Loop {
        public boolean calledNext = false;
        @Override
        public void next(Space space) {
            calledNext = true;
        }
    }
}
