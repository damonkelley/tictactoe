import java.util.*;
import java.util.stream.Collectors;

public class State {
    private List<Player> board;

    private static final int[][] wins = new int[][]{
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {6, 4, 2}
    };

    public State() {
        this.board = new ArrayList<Player>();

        for (int i = 0; i < 9; i++) {
            this.board.add(null);
        }
    }

    public List<Player> getBoard() {
        return board;
    }

    public Player getWinner() {
        for (int[] win : wins) {
            if (isWinner(Player.X, win)) {
                return Player.X;
            } else if (isWinner(Player.O, win)) {
                return Player.O;
            }
        }
        return null;
    }

    private boolean isWinner(Player player, int[] win) {
        return player == board.get(win[0]) &&
                player == board.get(win[1]) &&
                player == board.get(win[2]);
    }

    public boolean isOver() {
        return hasWinner() || isDraw();
    }

    private boolean hasWinner() {
        return getWinner() != null;
    }

    public boolean isDraw() {
        List<Player> movesLeft = board.stream()
                .filter(m -> m == null)
                .collect(Collectors.toList());
        return movesLeft.size() == 0 && !hasWinner();
    }

    public void move(int spaceId, Player marker) {
        board.set(spaceId, marker);
    }
}
