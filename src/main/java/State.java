import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public Player getWinner() {
        for (List<Point> win : wins) {
            if (isWinner(Player.X, win)) {
                return Player.X;
            } else if (isWinner(Player.O, win)) {
                return Player.O;
            }
        }
        return null;
    }

    private boolean isWinner(Player player, List<Point> win) {
        return win.stream()
                .map(p -> board.get(p) == player)
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

    public void move(Point point, Player marker) {
        board.put(point, marker);
    }
}
