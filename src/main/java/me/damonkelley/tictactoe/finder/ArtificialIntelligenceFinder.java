package me.damonkelley.tictactoe.finder;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Space;

import java.util.HashMap;

public class ArtificialIntelligenceFinder extends Finder {
    private Marker marker;
    private Space choice;

    private final int FOUR_BY_FOUR_DEPTH = 4;
    private final int THREE_BY_THREE_DEPTH = 6;

    public ArtificialIntelligenceFinder(Marker marker) {
        this.marker = marker;
    }

    @Override
    public Space getNextMove(Game game) {
        minimax(game, getOptimumDepthFor(game), true);
        return choice;
    }

    private int getOptimumDepthFor(Game game) {
        if (game.getBoard().getSize() == 4) {
            return FOUR_BY_FOUR_DEPTH;
        } else {
            return THREE_BY_THREE_DEPTH;
        }
    }

    private int minimax(Game game, int depth, boolean maximizingPlayer) {
        if (game.isOver() || depth == 0) return scoreFor(game, depth);

        HashMap<Integer, Space> scores = new HashMap<>();

        for (Space space : game.getBoard().availableSpaces()) {
            Game futureGame = game.copy();
            futureGame.move(space, futureGame.nextTurn());

            scores.put(minimax(futureGame, depth - 1, !maximizingPlayer), space);
        }

        int bestScore = bestScoreFor(maximizingPlayer, scores);
        choice = scores.get(bestScore);

        return bestScore;
    }

    private int scoreFor(Game game, int depth) {
        if (game.determineWinner() == marker) {
            return 10 + depth;
        } else if (game.isOver() && !game.isDraw()) {
            return -10 - depth;
        }
        return depth;
    }

    private int bestScoreFor(boolean maximizingPlayer, HashMap<Integer, Space> scores) {
        if (maximizingPlayer) {
            return findMaximumScore(scores);
        } else {
            return findMinimumScore(scores);
        }
    }

    private Integer findMinimumScore(HashMap<Integer, Space> scores) {
        return scores.keySet().stream()
                .reduce(Integer.MAX_VALUE, Integer::min);
    }

    private Integer findMaximumScore(HashMap<Integer, Space> scores) {
        return scores.keySet().stream()
                .reduce(Integer.MIN_VALUE, Integer::max);
    }
}
