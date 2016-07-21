package me.damonkelley.tictactoe_app;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Space;

class HumanTurnRunnable implements Runnable {
    private final Space space;
    private final Game game;

    public HumanTurnRunnable(Space space, Game game) {
        this.space = space;
        this.game = game;
    }

    @Override
    public void run() {
        game.move(space, game.nextTurn());
    }
}
