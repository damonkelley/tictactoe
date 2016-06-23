public class Game {

    private State state;
    private Player player1;
    private Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        state = new State(player1.getMarker());
    }

    public void nextMove() {
        nextTurn().move(state);
    }

    private Player nextTurn() {
        if (state.getNextMarker() == player1.getMarker()) {
            return player1;
        } else {
            return player2;
        }
    }

    public Player getWinner() {
        Marker winningMarker = state.getWinner();

        if (winningMarker == player1.getMarker()) {
            return player1;
        } else if (winningMarker == player2.getMarker()) {
            return player2;
        }

        return null;
    }

    public boolean isOver() {
        return state.isOver();
    }

    public boolean isDraw() {
        return state.isDraw();
    }

    public Board getBoard() {
        return state.getBoard();
    }
}
