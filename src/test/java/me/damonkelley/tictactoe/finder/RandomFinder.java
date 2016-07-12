package me.damonkelley.tictactoe.finder;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Space;

import java.util.Random;

class RandomFinder extends Finder {
    private Random generator = new Random();

    @Override
    public Space getNextMove(Game game) {
        int index = generator.nextInt(game.getBoard().availableSpaces().size());
        return game.getBoard().availableSpaces().get(index);
    }
}
