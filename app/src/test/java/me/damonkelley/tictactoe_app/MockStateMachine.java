package me.damonkelley.tictactoe_app;

import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe_app.turn.Turn;

public class MockStateMachine implements StateMachine {
    private StringBuffer log;
    private String logMessage;

    public MockStateMachine(StringBuffer log, String logMessage) {
        this.log = log;
        this.logMessage = logMessage;
    }
    @Override
    public void next(Space space) {
        log.append(logMessage + " ");
    }

    @Override
    public MockStateMachine setNext(Turn turn) {
        log.append("set-next ");
        return this;
    }
}
