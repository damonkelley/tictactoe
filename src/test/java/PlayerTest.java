import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertEquals;

public class PlayerTest {

    @Test
    public void itIsConfiguredWithAMarker() {
        Player player = new Player(PlayerMarker.X);
        assertEquals(PlayerMarker.X, player.getMarker());
    }

    @Test
    public void itCanMakeAMove() {
        State state = new State();
        Player marker = new Player(PlayerMarker.O);

        marker.move(state, new Point(0, 0));
        assertEquals(PlayerMarker.O, state.getBoard().get(new Point(0, 0)));
    }
}
