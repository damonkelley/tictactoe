package me.damonkelley.tictactoe_app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RunnableTurnTest {
    StringBuffer log = new StringBuffer();

    @Test
    public void itRunsAndAdvancesTheStateMachine() {
        new RunnableTurn(runnable).go(new MockStateMachine(log, "called-next"));
        assertEquals("called-runnable called-next ", log.toString());
    }

    private Runnable runnable = () -> {
        log.append("called-runnable ");
    };
}
