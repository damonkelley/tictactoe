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

    @Override
    public String toString() {
        return "Game{" +
                "rules=" + rules +
                ", state=" + state +
                ", player1=" + player1 +
                ", player2=" + player2 +
                '}';
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
}
