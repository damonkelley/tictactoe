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
    public void xWinsTheGame() {
        state.move(new Point(0, 0), Marker.X);
        state.move(new Point(1, 0), Marker.O);
        state.move(new Point(1, 1), Marker.X);
        state.move(new Point(2, 0), Marker.O);
        state.move(new Point(2, 2), Marker.X);

        assertEquals(Marker.X, state.getWinner());
    }

    @Test
    public void oWinsTheGame() {
        state.move(new Point(0, 0), Marker.X);
        state.move(new Point(1, 0), Marker.O);
        state.move(new Point(2, 0), Marker.X);
        state.move(new Point(1, 1), Marker.O);
        state.move(new Point(2, 2), Marker.X);
        state.move(new Point(1, 2), Marker.O);

        assertEquals(Marker.O, state.getWinner());
    }

    @Test
    public void moveAddsMarker() {
        state.move(new Point(1, 0), Marker.X);
        assertEquals(Marker.X, state.getBoard().get(new Point(1, 0)));
    }

    @Test
    public void itOnlyAllowsMovesToAvailableSpaces() {
        state.move(new Point(0, 0), Marker.X);
        state.move(new Point(0, 0), Marker.O);

        assertEquals(Marker.X, state.getBoard().get(new Point(0, 0)));
        assertEquals(Marker.O, state.getNextMarker());
    }

    @Test
    public void gameIsOver() {
        assertEquals(false, state.isOver());

        state.move(new Point(0, 0), Marker.X);
        state.move(new Point(1, 0), Marker.X);
        state.move(new Point(2, 0), Marker.X);

        assertEquals(Marker.X, state.getWinner());
        assertEquals(true, state.isOver());
    }

    @Test
    public void gameIsOverWithADraw() {
        makeDraw();
        assertEquals(true, state.isDraw());
        assertEquals(true, state.isOver());
    }

    @Test
    public void gameIsDraw() {
        assertEquals(false, state.isDraw());
        makeDraw();
        assertEquals(true, state.isDraw());
    }

    private void makeDraw() {
        state.move(new Point(0, 0), Marker.X);
        state.move(new Point(2, 0), Marker.O);
        state.move(new Point(0, 2), Marker.X);
        state.move(new Point(0, 1), Marker.O);
        state.move(new Point(1, 1), Marker.X);
        state.move(new Point(2, 2), Marker.O);
        state.move(new Point(1, 0), Marker.X);
        state.move(new Point(1, 2), Marker.O);
        state.move(new Point(2, 1), Marker.X);
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