package me.damonkelley.tictactoe_app.turn;

import me.damonkelley.tictactoe.Space;

public class ComputerTurn extends Turn {
    private final Runnable runnable;

    public ComputerTurn(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void go(Space space) {
        this.runnable.run();
        loop.setNext(next);
    }
}
