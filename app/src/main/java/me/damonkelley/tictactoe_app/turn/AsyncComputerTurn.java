package me.damonkelley.tictactoe_app.turn;

import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe_app.task.ComputerTask;
import me.damonkelley.tictactoe_app.wrapper.GameViews;

public class AsyncComputerTurn extends Turn {
    private final GameViews views;

    public AsyncComputerTurn(GameViews views) {
        this.views = views;
    }

    @Override
    public void go(Space space) {
        new ComputerTask(marker, views).exec(game);
        loop.setNext(next);
    }

    @Override
    public void initialize() {
        loop.next(new Space(-1, -1));
    }
}
