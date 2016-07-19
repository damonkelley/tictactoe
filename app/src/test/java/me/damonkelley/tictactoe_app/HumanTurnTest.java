package me.damonkelley.tictactoe_app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HumanTurnTest {
    StringBuffer log = new StringBuffer();
    @Test
    public void theStateMachineIsNotAdvanced() {
        new HumanTurn(runnable).go(new MockStateMachine(log, "this-should-no-be-present-in-the-log"));
        assertEquals("runnable-called ", log.toString());
    }

    Runnable runnable = () -> {
        log.append("runnable-called ");
    };
}
