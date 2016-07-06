package me.damonkelley.tictactoe;

import java.util.List;

import static java.util.Arrays.asList;

public class Game {

    public static final List<List<Space>> wins = asList(
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
