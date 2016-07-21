package me.damonkelley.tictactoe_app;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Space;

class HumanTurnRunnable implements Runnable {
    private final Space space;
    private Marker marker;
    private final Game game;

    public HumanTurnRunnable(Space space, Marker marker, Game game) {
        this.space = space;
        this.marker = marker;
        this.game = game;
    }

    @Override
    public void run() {
        game.move(space, marker);
    }
}
