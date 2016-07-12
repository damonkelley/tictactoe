package me.damonkelley.tictactoe.finder;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Space;

import java.util.TreeMap;

public class ArtificialIntelligenceFinder extends Finder {
    private Marker marker;
    private Space choice;

    private final int DEPTH = 8;

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

        TreeMap<Integer, Space> scores = new TreeMap<>();

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

            choice = scores.lastEntry().getValue();
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

            choice = scores.firstEntry().getValue();
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
}
