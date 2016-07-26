package me.damonkelley.tictactoe_app;

import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe_app.turn.Turn;

public class Loop implements StateMachine {
    private Turn next;

    public Loop() {
        this.next = null;
    }

    public void next(Space space) {
        this.next.go(space);
    }

    public Loop setNext(Turn next) {
        this.next = next.setLoop(this);
        return this;
    }
}
