import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {
    @Test
    public void itUsesAMoveFinderToFindAMove() {
        State state = new State();
        HumanPlayer player = new HumanPlayer(Marker.O, new FakeFinder());

        player.move(state);
        player.move(state);

        assertEquals(player.getMarker(), state.getBoard().get(new Point(2, 2)));
    }

    private class FakeFinder implements Finder {
        public Point getNextMove() {
            return new Point(2, 2);
        }
    }
}
