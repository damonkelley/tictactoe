import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertEquals;

public class HumanPlayerTest {

    @Test
    public void itIsConfiguredWithAMarker() {
        Player player = new HumanPlayer(PlayerMarker.X);
        assertEquals(PlayerMarker.X, player.getMarker());
    }

    @Test
    public void itCanMakeAMove() {
        State state = new State();
        Player marker = new HumanPlayer(PlayerMarker.O);

        marker.move(state, new Point(0, 0));
        assertEquals(PlayerMarker.O, state.getBoard().get(new Point(0, 0)));
    }
}
