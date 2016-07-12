package me.damonkelley.fake;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe.finder.Finder;

public class NullFinder extends Finder {
    @Override
    public Space getNextMove(Game game) {
        return null;
    }
}
