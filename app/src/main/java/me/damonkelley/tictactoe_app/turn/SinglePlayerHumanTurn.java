package me.damonkelley.tictactoe_app.turn;

import me.damonkelley.tictactoe.IllegalMoveException;
import me.damonkelley.tictactoe.Space;

public class SinglePlayerHumanTurn extends Turn {
    public SinglePlayerHumanTurn() {}

    @Override
    public void go(Space space) {
        try {
            game.move(space, marker);
            loop.setNext(next).next(new Space(-1, -1));
        } catch (IllegalMoveException e) {}
        updater.update();
    }
}
