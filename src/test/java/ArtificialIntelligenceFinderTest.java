import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertEquals;

public class ArtificialIntelligenceFinderTest {
    @Test
    public void itWillChooseTheWinningSpaceIfThereIsOne() {
        ComputerPlayer player = new ComputerPlayer(Marker.X);
        State state = new State();

        state.move(new Point(0, 0), player.getMarker());
        state.move(new Point(2, 0), Marker.O);
        state.move(new Point(1, 1), player.getMarker());
        state.move(new Point(0, 2), Marker.O);

        ArtificialIntelligenceFinder finder = new ArtificialIntelligenceFinder(player, state);

        assertEquals(new Point(2, 2), finder.getNextMove());
    }

    @Test
    public void itWillPreventALoss() {
        ComputerPlayer player = new ComputerPlayer(Marker.O);
        State state = new State();

        state.move(new Point(0, 0), Marker.X);
        state.move(new Point(2, 0), player.getMarker());
        state.move(new Point(1, 1), Marker.X);

        ArtificialIntelligenceFinder finder = new ArtificialIntelligenceFinder(player, state);

        assertEquals(new Point(2, 2), finder.getNextMove());
    }
}
