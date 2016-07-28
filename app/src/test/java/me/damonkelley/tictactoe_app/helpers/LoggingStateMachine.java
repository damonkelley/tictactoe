package me.damonkelley.tictactoe_app.helpers;

import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe_app.loop.StateMachine;
import me.damonkelley.tictactoe_app.turn.Turn;

public class LoggingStateMachine implements StateMachine {
    public String log = "";

    @Override
    public void next(Space space) {
        log += "next ";
    }

    @Override
    public LoggingStateMachine setNext(Turn turn) {
        log += "set-next ";
        return this;
    }
}
