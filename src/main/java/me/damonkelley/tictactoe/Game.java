package me.damonkelley.tictactoe;

public class Game {

    private State state;
    private Marker initialMarker;

    public Game(Marker marker) {
        initialMarker = marker;
        state = new State(marker);
    }

    private Game(State state) {
        initialMarker = state.getNextMarker();
        this.state = state;
    }

    public Game move(Space space, Marker marker) {
        if (canMove(space)) {
            state.move(space, marker);
        }
        return this;
    }

    private boolean canMove(Space point) {
        return state.getBoard().get(point) == null;
    }

    public Marker nextTurn() {
        return state.getNextMarker();
    }

    public Marker determineWinner() {
        if (isWinner(Marker.X)) {
            return Marker.X;
        } else if (isWinner((Marker.O))) {
            return Marker.O;
        } else {
            return null;
        }
    }

    private boolean isWinner(Marker marker) {
        return new BoardFormatter(getBoard())
                .collect()
                .stream()
                .filter(collection -> collection.stream().allMatch(e -> e == marker))
                .findAny()
                .isPresent();
    }

    public boolean isOver() {
        return determineWinner() != null || isDraw();
    }

    public boolean isDraw() {
        return state.getBoard().isFull() && determineWinner() == null;
    }

    public Board getBoard() {
        return state.getBoard();
    }

    public Game reset() {
        state = new State(initialMarker);
        return this;
    }

    public Game copy() {
        return new Game(state.copy());
    }

    @Override
    public String toString() {
        return "Game{" +
                "state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        return state != null ? state.equals(game.state) : game.state == null;

    }
}
