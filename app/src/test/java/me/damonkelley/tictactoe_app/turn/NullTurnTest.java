package me.damonkelley.tictactoe_app.turn;

import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe_app.MockStateMachine;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NullTurnTest {
    StringBuffer log = new StringBuffer();

    @Test
    public void theStateMachineIsNotAdvanced() {
        new NullTurn()
                .setLoop(new MockStateMachine(log, "this-should-not-be-present-in-the-log"))
                .go(new Space(0, 0));

        assertEquals("", log.toString());
    }
}
