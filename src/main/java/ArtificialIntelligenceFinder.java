import java.util.HashMap;

class ArtificialIntelligenceFinder implements Finder {
    private State state;
    private Player player;
    private Space choice;

    public ArtificialIntelligenceFinder(ComputerPlayer player, State state) {
        this.player = player;
        this.state = state;
    }

    @Override
    public Space getNextMove() {
        minimax(state, 6, true);
        return choice;
    }

    private int minimax(State state, int depth, boolean maximizingPlayer) {
        if (new GameRules(state).isOver() || depth == 0) return scoreFor(state, depth);

        HashMap<Integer, Space> scores = new HashMap<>();

        for (Space space : state.getBoard().availableSpaces()) {
            State futureState = state.copy();
            futureState.move(space, futureState.getNextMarker());

            scores.put(minimax(futureState, depth - 1, !maximizingPlayer), space);
        }

        int bestScore = bestScoreFor(maximizingPlayer, scores);
        choice = scores.get(bestScore);

        return bestScore;
    }

    private int scoreFor(State state, int depth) {
        GameRules rules = new GameRules(state);
        if (rules.determineWinner() == player.getMarker()) {
            return 10 + depth;
        } else if (rules.isOver() && !rules.isDraw()) {
            return -10 - depth;
        }
        return 0;
    }

    private int bestScoreFor(boolean maximizingPlayer, HashMap<Integer, Space> scores) {
        if (maximizingPlayer) {
            return findMaximumScore(scores);
        } else {
            return findMinimumScore(scores);
        }
    }

    private Integer findMinimumScore(HashMap<Integer, Space> scores) {
        return scores.keySet().stream()
                .reduce(Integer.MAX_VALUE, Integer::min);
    }

    private Integer findMaximumScore(HashMap<Integer, Space> scores) {
        return scores.keySet().stream()
                .reduce(Integer.MIN_VALUE, Integer::max);
    }
}
