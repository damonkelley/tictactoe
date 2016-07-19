package me.damonkelley.tictactoe_app;

public class ComputerTurn implements Turn {
    private final Runnable runnable;

    public ComputerTurn(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void go(StateMachine machine) {
        this.runnable.run();
        machine.next();
    }
}
