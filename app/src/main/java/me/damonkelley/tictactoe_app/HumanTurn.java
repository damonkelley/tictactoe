package me.damonkelley.tictactoe_app;

public class HumanTurn implements Turn {
    private final Runnable runnable;

    public HumanTurn(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void go(StateMachine machine) {
        runnable.run();
    }
}
