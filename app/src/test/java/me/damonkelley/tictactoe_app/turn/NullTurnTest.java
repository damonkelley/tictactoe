package me.damonkelley.tictactoe_app.turn;

import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe_app.helpers.LoggingStateMachine;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NullTurnTest {
    StringBuffer log = new StringBuffer();

    @Test
    public void theStateMachineIsNotAdvanced() {
        LoggingStateMachine machine = new LoggingStateMachine();
        new NullTurn()
                .setLoop(machine)
                .go(new Space(0, 0));

        assertEquals("", log.toString());
    }
}
