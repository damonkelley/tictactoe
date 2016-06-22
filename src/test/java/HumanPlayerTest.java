import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class HumanPlayerTest {

    @Test
    public void itIsConfiguredWithAMarker() {
        Player player = new HumanPlayer(PlayerMarker.X);
        assertEquals(PlayerMarker.X, player.getMarker());
    }

    @Test
    public void itCanMakeAMove() {
        State state = new State();
        HumanPlayer player = new HumanPlayer(PlayerMarker.O);

        player.queueMove(new Point(0, 0));
        player.move(state);

        assertEquals(PlayerMarker.O, state.getBoard().get(new Point(0, 0)));
    }

    @Test
    public void itIsEqualToAnotherPlayerWithTheSameMarker() {
        assertEquals(new HumanPlayer(PlayerMarker.X), new HumanPlayer(PlayerMarker.X));
        assertNotEquals(new HumanPlayer(PlayerMarker.O), new HumanPlayer(PlayerMarker.X));
    }
}
