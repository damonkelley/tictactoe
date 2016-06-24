import java.util.List;

import static java.util.Arrays.asList;

public class GameRules {
    private static final List<List<Space>> wins = asList(
            asList(new Space(0, 0), new Space(1, 0), new Space(2, 0)), // top row
            asList(new Space(0, 1), new Space(1, 1), new Space(2, 1)), // middle row
            asList(new Space(0, 2), new Space(1, 2), new Space(2, 2)), // bottom row
            asList(new Space(0, 0), new Space(0, 1), new Space(0, 2)), // left column
            asList(new Space(1, 0), new Space(1, 1), new Space(1, 2)), // middle column
            asList(new Space(2, 0), new Space(2, 1), new Space(2, 2)), // right column
            asList(new Space(0, 0), new Space(1, 1), new Space(2, 2)), // diagonal 1
            asList(new Space(0, 2), new Space(1, 1), new Space(2, 0))  // diagonal 2
    );
    private State state;

    public GameRules(State state) {
        this.state = state;
    }

    public boolean canMove(State state, Space point) {
        this.state = state;
        return state.getBoard().get(point) == null;
    }

    public Marker determineWinner() {
        for (List<Space> win : wins) {
            if (isWinner(Marker.X, win)) {
                return Marker.X;
            } else if (isWinner(Marker.O, win)) {
                return Marker.O;
            }
        }
        return null;
    }

    private boolean isWinner(Marker marker, List<Space> win) {
        return win.stream()
                .map(p -> state.getBoard().get(p) == marker)
                .allMatch(b -> b);
    }

    public boolean isDraw() {
        return state.getBoard().isFull() && determineWinner() == null;
    }

    public boolean isOver() {
        return determineWinner() != null || isDraw();
    }
}
