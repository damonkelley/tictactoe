package me.damonkelley.tictactoe_app.turn;

import me.damonkelley.tictactoe.Space;

public class MultiPlayerHumanTurn extends Turn {
    @Override
    public void go(Space space) {
        game.move(space, marker);
        loop.setNext(next);
        updater.update();
    }
}
