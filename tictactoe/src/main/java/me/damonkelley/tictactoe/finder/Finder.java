package me.damonkelley.tictactoe.finder;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Space;

public abstract class Finder {
    abstract public Space getNextMove(Game game);

    public boolean equals(Object o) {
        if (o != null && getClass() == o.getClass()) return true;
        return false;
    }
}
