import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class State {
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
    private Board board;
    private Marker nextMarker;

    public State() {
        this.nextMarker = Marker.X;
        this.board = new Board();
    }

    private State(Board board, Marker nextMarker) {
        this.board = board;
        this.nextMarker = nextMarker;
    }

    public State(Marker o) {
        this();
        this.nextMarker = o;
    }

    public Board getBoard() {
        return board;
    }

    public Marker getWinner() {
        for (List<Point> win : wins) {
            if (isWinner(Marker.X, win)) {
                return Marker.X;
            } else if (isWinner(Marker.O, win)) {
                return Marker.O;
            }
        }
        return null;
    }

    private boolean isWinner(Marker marker, List<Point> win) {
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

    public State move(Point point, Marker marker) {
        if (board.get(point) == null) {
            board.put(point, marker);
            alternateCurrentTurn(marker);
        }
        return this;
    }

    private void alternateCurrentTurn(Marker marker) {
        nextMarker = (marker == Marker.X) ? Marker.O : Marker.X;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        return board != null ? board.equals(state.board) : state.board == null;
    }

    @Override
    public int hashCode() {
        return board != null ? board.hashCode() : 0;
    }

    public State copy() {
        return new State(this.board.copy(), this.nextMarker);
    }

    public Marker getNextMarker() {
        return nextMarker;
    }
}
