package me.damonkelley.tictactoe;

class State {
    private Board board;
    private Marker nextMarker;

    State() {
        this.nextMarker = Marker.X;
        this.board = new Board();
    }

    State(Marker marker) {
        this();
        this.nextMarker = marker;
    }

    public State(Board board, Marker nextMarker) {
        this.board = board;
        this.nextMarker = nextMarker;
    }

    Board getBoard() {
        return board;
    }

    State move(Space point, Marker marker) {
        board.put(point, marker);
        alternateCurrentTurn();
        return this;
    }

    private void alternateCurrentTurn() {
        nextMarker = (nextMarker == Marker.X) ? Marker.O : Marker.X;
    }

    State copy() {
        return new State(this.board.copy(), this.nextMarker);
    }

    Marker getNextMarker() {
        return nextMarker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        return board != null ? board.equals(state.board) : state.board == null;
    }
}
