import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertEquals;

public class ComputerPlayerTest {
    @Test
    public void itIsConfiguredWithAPlayerMarker() {
        ComputerPlayer player = new ComputerPlayer(PlayerMarker.O);
        assertEquals(PlayerMarker.O, player.getMarker());
    }

    @Test
    public void itCanMakeAMove() {
        ComputerPlayer player = new ComputerPlayer(PlayerMarker.X);
        State state = new State();
        player.move(state);

        assertEquals(PlayerMarker.X, state.getBoard().get(new Point(0, 0)));
    }
}
