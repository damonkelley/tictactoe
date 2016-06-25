public class Game {

    private GameRules rules;
    private State state;
    private Player player1;
    private Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        state = new State(player1.getMarker());
        rules = new GameRules(state);
    }

    public void nextMove() {
        nextTurn().move(this);
    }

    private Player nextTurn() {
        if (state.getNextMarker() == player1.getMarker()) {
            return player1;
        } else {
            return player2;
        }
    }

    public State getState() {
        return state;
    }

    public Player getWinner() {
        Marker winningMarker = rules.determineWinner();

        if (winningMarker == player1.getMarker()) {
            return player1;
        } else if (winningMarker == player2.getMarker()) {
            return player2;
        }

        return null;
    }

    public boolean isOver() {
        return rules.isOver();
    }

    public boolean isDraw() {
        return rules.isDraw();
    }

    public Board getBoard() {
        return state.getBoard();
    }
}
