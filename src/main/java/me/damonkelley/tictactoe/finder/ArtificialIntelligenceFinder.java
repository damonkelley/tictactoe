package me.damonkelley.tictactoe.finder;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.GameRules;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe.State;

import java.util.HashMap;

public class ArtificialIntelligenceFinder extends Finder {
    private Marker marker;
    private Space choice;

    public ArtificialIntelligenceFinder(Marker marker) {
        this.marker = marker;
    }

    @Override
    public Space getNextMove(Game game) {
        minimax(game.getState(), 6, true);
        return choice;
    }

    private int minimax(State state, int depth, boolean maximizingPlayer) {
        if (new GameRules(state).isOver() || depth == 0) return scoreFor(state, depth);

        HashMap<Integer, Space> scores = new HashMap<>();

        for (Space space : state.getBoard().availableSpaces()) {
            State futureState = state.copy();
            futureState.move(space, futureState.getNextMarker());

            scores.put(minimax(futureState, depth - 1, !maximizingPlayer), space);
        }

        int bestScore = bestScoreFor(maximizingPlayer, scores);
        choice = scores.get(bestScore);

        return bestScore;
    }

    private int scoreFor(State state, int depth) {
        GameRules rules = new GameRules(state);
        if (rules.determineWinner() == marker) {
            return 10 + depth;
        } else if (rules.isOver() && !rules.isDraw()) {
            return -10 - depth;
        }
        return 0;
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
