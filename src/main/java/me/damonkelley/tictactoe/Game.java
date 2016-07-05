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
    private Player player1;
    private Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        state = new State(player1.getMarker());
    }

    private Game(Player player1, Player player2, State state) {
        this.player1 = player1;
        this.player2 = player2;
        this.state = state;
    }

    public Game move(Space space, Marker marker) {
        if (canMove(space)) {
            state.move(space, marker);
        }
        return this;
    }

    public boolean canMove(Space point) {
        return state.getBoard().get(point) == null;
    }

    public Game nextMove() {
        nextPlayer().move(this);
        return this;
    }

    private Player nextPlayer() {
        if (state.getNextMarker() == player1.getMarker()) {
            return player1;
        } else {
            return player2;
        }
    }

    public Marker nextTurn() {
        return state.getNextMarker();
    }

    public Player getWinner() {
        Marker winningMarker = determineWinner();

        if (winningMarker == player1.getMarker()) {
            return player1;
        } else if (winningMarker == player2.getMarker()) {
            return player2;
        }

        return null;
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
        state = new State(player1.getMarker());
        return this;
    }

    public Game copy() {
        return new Game(player1, player2, state.copy());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (state != null ? !state.equals(game.state) : game.state != null) return false;
        if (player1 != null ? !player1.equals(game.player1) : game.player1 != null) return false;
        return player2 != null ? player2.equals(game.player2) : game.player2 == null;
    }

    @Override
    public String toString() {
        return "me.damonkelley.tictactoe.Game{" +
                "state=" + state +
                ", player1=" + player1 +
                ", player2=" + player2 +
                '}';
    }
}
