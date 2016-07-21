package me.damonkelley.tictactoe_app;

public class RunnableTurn implements Turn {
    private final Runnable runnable;

    public RunnableTurn(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void go(StateMachine machine) {
        this.runnable.run();
        machine.next();
    }
}
