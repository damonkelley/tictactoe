import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class State {
    private Board board;

    private static final List<List<Point>> wins = Arrays.asList(
            Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(2, 0)), // top row
            Arrays.asList(new Point(0, 1), new Point(1, 1), new Point(2, 1)), // middle row
            Arrays.asList(new Point(0, 2), new Point(1, 2), new Point(2, 2)), // bottom row
            Arrays.asList(new Point(0, 0), new Point(0, 1), new Point(0, 2)), // left column
            Arrays.asList(new Point(1, 0), new Point(1, 1), new Point(1, 2)), // middle column
            Arrays.asList(new Point(2, 0), new Point(2, 1), new Point(2, 2)), // right column
            Arrays.asList(new Point(0, 0), new Point(1, 1), new Point(2, 2)), // diagonal 1
            Arrays.asList(new Point(0, 2), new Point(1, 1), new Point(2, 0))  // diagonal 2
    );

    public State() {
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public PlayerMarker getWinner() {
        for (List<Point> win : wins) {
            if (isWinner(PlayerMarker.X, win)) {
                return PlayerMarker.X;
            } else if (isWinner(PlayerMarker.O, win)) {
                return PlayerMarker.O;
            }
        }
        return null;
    }

    private boolean isWinner(PlayerMarker marker, List<Point> win) {
        return win.stream()
                .map(p -> board.get(p) == marker)
                .allMatch(b -> b);
    }

    public boolean isOver() {
        return hasWinner() || isDraw();
    }

    private boolean hasWinner() {
        return getWinner() != null;
    }

    public boolean isDraw() {
        return board.isFull() && !hasWinner();
    }

    public void move(Point point, PlayerMarker marker) {
        board.put(point, marker);
    }
}
