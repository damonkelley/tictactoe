package me.damonkelley.tictactoe_app;

import android.os.AsyncTask;
import me.damonkelley.tictactoe.Game;

public class ComputerTurnRunnable implements Runnable {
    private final Game game;
    private final AsyncTask task;

    public ComputerTurnRunnable(Game game, GameViews views) {
        this.game = game;
        this.task = new ComputerTask(views);
    }

    @Override
    public void run() {
        task.execute(game);
    }
}
