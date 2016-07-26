package me.damonkelley.tictactoe_app.turn;

import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe_app.MockStateMachine;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ComputerTurnTest {
    StringBuffer log = new StringBuffer();

    @Test
    public void itRunsSetsTheNextTurn() {
        new ComputerTurn(runnable)
                .setLoop(new MockStateMachine(log, "called-next"))
                .go(new Space(-1, -1));

        assertEquals("called-runnable set-next ", log.toString());
    }

    private Runnable runnable = () -> {
        log.append("called-runnable ");
    };
}
