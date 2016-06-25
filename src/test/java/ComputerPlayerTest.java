import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ComputerPlayerTest {

    @Test
    public void itIsConfiguredWithAPlayerMarker() {
        ComputerPlayer player = new ComputerPlayer(Marker.O);
        assertEquals(Marker.O, player.getMarker());
    }

    @Test
    public void itWillChooseTheWinningSpaceIfThereIsOne() {
        ComputerPlayer computerPlayer = new ComputerPlayer(Marker.X);
        Player player = new Player(Marker.O, new NullFinder());
        State state = new State();

        state.move(new Space(0, 0), computerPlayer.getMarker());
        state.move(new Space(2, 0), player.getMarker());
        state.move(new Space(1, 1), computerPlayer.getMarker());
        state.move(new Space(0, 2), player.getMarker());

        computerPlayer.move(state);

        assertEquals(computerPlayer.getMarker(), new GameRules(state).determineWinner());
    }

    @Test
    public void itWillPreventALoss() {
        ComputerPlayer computerPlayer = new ComputerPlayer(Marker.O);
        Player player = new Player(Marker.X, new NullFinder());
        State state = new State();

        state.move(new Space(0, 0), player.getMarker());
        state.move(new Space(2, 0), computerPlayer.getMarker());
        state.move(new Space(1, 1), player.getMarker());

        computerPlayer.move(state);

        assertEquals(computerPlayer.getMarker(), state.getBoard().get(new Space(2, 2)));
    }

    private class NullFinder implements Finder {

        @Override
        public Space getNextMove(State state) {
            return null;
        }
    }

}
