import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ArtificialIntelligenceFinderTest {
    @Test
    public void itWillChooseTheWinningSpaceIfThereIsOne() {
        State state = new State();

        state.move(new Space(0, 0), Marker.X);
        state.move(new Space(2, 0), Marker.O);
        state.move(new Space(1, 1), Marker.X);
        state.move(new Space(0, 2), Marker.O);

        ArtificialIntelligenceFinder finder = new ArtificialIntelligenceFinder(Marker.X);

        assertEquals(new Space(2, 2), finder.getNextMove(state));
    }

    @Test
    public void itWillPreventALoss() {
        State state = new State();

        state.move(new Space(0, 0), Marker.X);
        state.move(new Space(2, 0), Marker.O);
        state.move(new Space(1, 1), Marker.X);

        ArtificialIntelligenceFinder finder = new ArtificialIntelligenceFinder(Marker.O);

        assertEquals(new Space(2, 2), finder.getNextMove(state));
    }
}
