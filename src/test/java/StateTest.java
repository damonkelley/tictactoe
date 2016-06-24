import org.junit.Before;
import org.junit.Test;

import java.awt.Point;

import static org.junit.Assert.*;


public class StateTest {
    private State state;

    @Before
    public void setUp() throws Exception {
        this.state = new State();
    }

    @Test
    public void moveAddsMarker() {
        state.move(new Point(1, 0), Marker.X);
        assertEquals(Marker.X, state.getBoard().get(new Point(1, 0)));
    }

    @Test
    public void itAllowsMovesToUnavailableSpaces() {
        state.move(new Point(0, 0), Marker.X);
        state.move(new Point(0, 0), Marker.O);

        assertEquals(Marker.O, state.getBoard().get(new Point(0, 0)));
    }

    @Test
    public void itCanTestEqualityWithOtherStates() {
        this.state.move(new Point(1, 0), Marker.X);

        State newState = new State();
        assertNotEquals(newState, this.state);

        newState.move(new Point(1, 0), Marker.X);
        assertEquals(newState, this.state);
    }

    @Test
    public void itCanMakeACopyOfItself() {
        assertEquals(this.state, this.state.copy());
        assertEquals(this.state.getNextMarker(), this.state.copy().getNextMarker());

        State newState = this.state.copy()
                .move(new Point(0, 1), Marker.X);

        assertNotEquals(this.state, newState);
        assertEquals(Marker.O, newState.getNextMarker());
    }

    @Test
    public void itKnowsTheNextPlayerToMakeAMove() {
        assertEquals(Marker.X, this.state.getNextMarker());

        this.state.move(new Point(0, 0), Marker.X);
        assertEquals(Marker.O, this.state.getNextMarker());

        this.state.move(new Point(0, 2), Marker.O);
        assertEquals(Marker.X, this.state.getNextMarker());
    }

    @Test
    public void itIsConfiguredWithTheFirstMarkerToMove() {
        assertEquals(Marker.O, new State(Marker.O).getNextMarker());
        assertEquals(Marker.X, new State(Marker.X).getNextMarker());
    }

}