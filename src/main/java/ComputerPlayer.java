import java.awt.Point;
import java.util.HashMap;

public class ComputerPlayer extends Player {

    public ComputerPlayer(Marker marker) {
        super(marker);
    }

    public void move(State state) {
        Finder finder = new Finder(this);
        finder.minimax(state, 6, true);
        state.move(finder.choice, getMarker());
    }

    private class Finder {
        public Point choice;
        private ComputerPlayer player;

        public Finder(ComputerPlayer player) {
            this.player = player;
        }

        private int minimax(State state, int depth, boolean maximizingPlayer) {
            if (state.isOver() || depth == 0) return score(state, depth);

            HashMap<Integer, Point> scores = new HashMap<>();

            for (Point space : state.getBoard().availableSpaces()) {
                State futureState = state.copy();
                futureState.move(space, futureState.getNextMarker());

                scores.put(minimax(futureState, depth - 1, !maximizingPlayer), space);
            }

            int bestScore = getBestScore(maximizingPlayer, scores);
            choice = scores.get(bestScore);

            return bestScore;
        }

        private int score(State state, int depth) {
            if (state.getWinner() == player.getMarker()) {
                return 10 + depth;
            } else if (state.isOver() && !state.isDraw()) {
                return -10 - depth;
            }
            return 0;
        }

        private int getBestScore(boolean maximizingPlayer, HashMap<Integer, Point> scores) {
            if (maximizingPlayer) {
                return findMaximumScore(scores);
            } else {
                return findMinimumScore(scores);
            }
        }

        private Integer findMinimumScore(HashMap<Integer, Point> scores) {
            return scores.keySet().stream()
                    .reduce(Integer.MAX_VALUE, Integer::min);
        }

        private Integer findMaximumScore(HashMap<Integer, Point> scores) {
            return scores.keySet().stream()
                    .reduce(Integer.MIN_VALUE, Integer::max);
        }

    }

}
