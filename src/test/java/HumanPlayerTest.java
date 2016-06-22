import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class HumanPlayerTest {

    @Test
    public void itIsConfiguredWithAMarker() {
        Player player = new HumanPlayer(Marker.X);
        assertEquals(Marker.X, player.getMarker());
    }

    @Test
    public void itCanMakeAMove() {
        State state = new State();
        HumanPlayer player = new HumanPlayer(Marker.O);

        player.queueMove(new Point(0, 0));
        player.move(state);

        assertEquals(Marker.O, state.getBoard().get(new Point(0, 0)));
    }

    @Test
    public void itIsEqualToAnotherPlayerWithTheSameMarker() {
        assertEquals(new HumanPlayer(Marker.X), new HumanPlayer(Marker.X));
        assertNotEquals(new HumanPlayer(Marker.O), new HumanPlayer(Marker.X));
    }
}
