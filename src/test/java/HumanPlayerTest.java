import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {
    @Test
    public void itUsesAMoveFinderToFindAMove() {
        State state = new State();
        HumanPlayer player = new HumanPlayer(Marker.O, new FakeFinder());

        player.move(state);
        player.move(state);

        assertEquals(player.getMarker(), state.getBoard().get(new Space(2, 2)));
    }

    private class FakeFinder implements Finder {
        public Space getNextMove() {
            return new Space(2, 2);
        }
    }
}
