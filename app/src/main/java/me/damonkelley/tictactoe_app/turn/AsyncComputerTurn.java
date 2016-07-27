package me.damonkelley.tictactoe_app.turn;

import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe_app.task.ComputerTask;

public class AsyncComputerTurn extends Turn {
    @Override
    public void go(Space space) {
        new ComputerTask(marker, updater).exec(game);
        loop.setNext(next);
    }

    @Override
    public void initialize() {
        loop.next(new Space(-1, -1));
    }
}
