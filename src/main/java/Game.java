import java.util.*;
import java.util.stream.Collectors;

public class Game {
    private String input;
    private StringBuffer output;
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

    public Game(String input, StringBuffer output) {
        this.input = input;
        this.output = output;
        this.board = new ArrayList<Player>();

        for (int i = 0; i < 9; i++) {
            this.board.add(null);
        }
    }

    public void play() {
        int turn = 0;
        for (String rawMove : input.split("\n")) {
            if (!rawMove.isEmpty()) {
                int spaceId = Integer.parseInt(rawMove);
                if (turn % 2 == 0) {
                    move(spaceId, Player.X);
                } else {
                    move(spaceId, Player.O);
                }
                turn++;
            }
        }
        new BoardFormatter(this.output, this.board).render();
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

    private class BoardFormatter {
        private final StringBuffer output;
        private final List<Player> moves;

        BoardFormatter(StringBuffer output, List<Player> moves) {
            this.output = output;
            this.moves = moves;
        }

        public void render() {
            ListIterator<Player> movesIterator = moves.listIterator();
            Player nextMove;
            String marker;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    nextMove = movesIterator.next();
                    if (nextMove == null) {
                        marker = " ";
                    } else {
                        marker = nextMove.toString();
                    }

                    if (j < 2) {
                        this.output.append(" ").append(marker).append(" |");
                    } else {
                        this.output.append(" ").append(marker).append(" \n");
                    }

                }
                if (i < 2) {
                    this.output.append("---+---+---\n");
                }
            }
        }
    }
}
