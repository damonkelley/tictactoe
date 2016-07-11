package me.damonkelley.tictactoe.finder;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Space;

import java.util.Map;
import java.util.TreeMap;

public class ArtificialIntelligenceFinder extends Finder {
    private Marker marker;
    private Space choice;

    private final int DEPTH = 7;

    public ArtificialIntelligenceFinder(Marker marker) {
        this.marker = marker;
    }

    @Override
    public Space getNextMove(Game game) {
        minimax(game, DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        return choice;
    }

    private int minimax(Game game, int depth, int alpha, int beta, boolean maximizingPlayer) {
        if (game.isOver() || depth == 0) return scoreFor(game, depth);

        Map<Integer, Space> scores = new TreeMap<>();

        if (maximizingPlayer) {
            int score = alpha;
            for (Space space : game.getBoard().availableSpaces()) {
                Game futureGame = game.copy()
                        .move(space, game.nextTurn());

                score = Integer.max(score, minimax(futureGame, depth - 1, alpha, beta, !maximizingPlayer));
                scores.putIfAbsent(score, space);

                alpha = Integer.max(alpha, score);
                if (beta <= alpha)
                    break;
            }

            choice = scores.get(findMaximumScore(scores));
            return score;
        } else {
            int score = beta;
            for (Space space : game.getBoard().availableSpaces()) {
                Game futureGame = game.copy()
                        .move(space, game.nextTurn());

                score = Integer.min(score, minimax(futureGame, depth - 1, alpha, beta, !maximizingPlayer));
                scores.putIfAbsent(score, space);

                beta = Integer.min(score, beta);
                if (beta <= alpha)
                    break;
            }

            choice = scores.get(findMinimumScore(scores));
            return score;
        }
    }

    private int scoreFor(Game game, int depth) {
        if (game.isWinner(marker)) {
            return 10 + depth;
        } else if (game.isOver() && !game.isDraw()) {
            return -10 - depth;
        }
        return depth;
    }

    private Integer findMinimumScore(Map<Integer, Space> scores) {
        return scores.keySet().stream()
                .reduce(Integer.MAX_VALUE, Integer::min);
    }

    private Integer findMaximumScore(Map<Integer, Space> scores) {
        return scores.keySet().stream()
                .reduce(Integer.MIN_VALUE, Integer::max);
    }
}
