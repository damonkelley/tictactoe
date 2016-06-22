import org.junit.Test;

import java.awt.*;

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
        Player player = new QueueBackedPlayer(Marker.O);
        State state = new State();

        state.move(new Point(0, 0), computerPlayer.getMarker());
        state.move(new Point(2, 0), player.getMarker());
        state.move(new Point(1, 1), computerPlayer.getMarker());
        state.move(new Point(0, 2), player.getMarker());

        computerPlayer.move(state);

        assertEquals(computerPlayer.getMarker(), state.getWinner());
    }

    @Test
    public void itWillPreventALoss() {
        ComputerPlayer computerPlayer = new ComputerPlayer(Marker.O);
        Player player = new QueueBackedPlayer(Marker.X);
        State state = new State();

        state.move(new Point(0, 0), player.getMarker());
        state.move(new Point(2, 0), computerPlayer.getMarker());
        state.move(new Point(1, 1), player.getMarker());

        computerPlayer.move(state);

        assertEquals(computerPlayer.getMarker(), state.getBoard().get(new Point(2, 2)));
    }

}
