package me.damonkelley.tictactoe_app;

import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe_app.turn.Turn;

public interface StateMachine {
    void next(Space space);
    StateMachine setNext(Turn turn);
}
