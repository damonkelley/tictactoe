package me.damonkelley.tictactoe_app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NullTurnTest {
    StringBuffer log = new StringBuffer();
    @Test
    public void theStateMachineIsNotAdvanced() {
        new NullTurn().go(new MockStateMachine(log, "this-should-not-be-present-in-the-log"));
        assertEquals("", log.toString());
    }
}
