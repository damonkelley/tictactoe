import java.awt.*;

public class State {
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

    public State move(Point point, Marker marker) {
        if (board.get(point) == null) {
            board.put(point, marker);
            alternateCurrentTurn();
        }
        return this;
    }

    private void alternateCurrentTurn() {
        nextMarker = (nextMarker == Marker.X) ? Marker.O : Marker.X;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        return board != null ? board.equals(state.board) : state.board == null;
    }

    public State copy() {
        return new State(this.board.copy(), this.nextMarker);
    }

    public Marker getNextMarker() {
        return nextMarker;
    }
}
