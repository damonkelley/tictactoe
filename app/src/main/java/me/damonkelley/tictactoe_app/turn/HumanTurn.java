package me.damonkelley.tictactoe_app.turn;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.IllegalMoveException;
import me.damonkelley.tictactoe.Space;

public class HumanTurn extends Turn {
    private Space space;

    public HumanTurn(Game game, Space space) {
        this.game = game;
        this.space = space;
    }

    public HumanTurn() {}

    @Override
    public void go(Space space) {
        try {
            game.move(space, marker);
            loop.setNext(next).next(new Space(-1, -1));
        } catch (IllegalMoveException e) {}
    }
}
