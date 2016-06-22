import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class QueueBackedPlayerTest {

    @Test
    public void itIsConfiguredWithAMarker() {
        Player player = new QueueBackedPlayer(Marker.X);
        assertEquals(Marker.X, player.getMarker());
    }

    @Test
    public void itCanMakeAMove() {
        State state = new State();
        QueueBackedPlayer player = new QueueBackedPlayer(Marker.O);

        player.queueMove(new Point(0, 0));
        player.move(state);

        assertEquals(Marker.O, state.getBoard().get(new Point(0, 0)));
    }

    @Test
    public void itIsEqualToAnotherPlayerWithTheSameMarker() {
        assertEquals(new QueueBackedPlayer(Marker.X), new QueueBackedPlayer(Marker.X));
        assertNotEquals(new QueueBackedPlayer(Marker.O), new QueueBackedPlayer(Marker.X));
    }
}
