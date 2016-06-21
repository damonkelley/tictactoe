import java.awt.Point;
import java.util.HashMap;

public class ComputerPlayer {
    private PlayerMarker marker;

    public ComputerPlayer(PlayerMarker marker) {
        this.marker = marker;
    }

    public void move(State state) {
        Finder finder = new Finder(this);
        finder.minimax(state, 6, true);
        state.move(finder.choice, getMarker());
    }

    public PlayerMarker getMarker() {
        return marker;
    }

    private class Finder {
        public Point choice;
        private ComputerPlayer player;

        public Finder(ComputerPlayer player) {
            this.player = player;
        }

        private int minimax(State state, int depth, boolean maximizingPlayer) {
            if (state.isOver() || depth == 0) {
                return score(state, depth);
            }

            HashMap<Integer, Point> scores = new HashMap<>();

            for (Point space : state.getBoard().availableSpaces()) {
                State futureState = state.copy();
                futureState.move(space, futureState.getNextTurnMarker());

                scores.put(minimax(futureState, depth - 1, !maximizingPlayer), space);
            }

            int bestScore;
            if (maximizingPlayer) {
                bestScore = scores.keySet().stream()
                        .reduce(Integer.MIN_VALUE, Integer::max);
            } else {
                bestScore = scores.keySet().stream()
                        .reduce(Integer.MAX_VALUE, Integer::min);
            }

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
    }

}
