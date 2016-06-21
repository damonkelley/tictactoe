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
        state.move(new Point(0, 0), PlayerMarker.X);
        state.move(new Point(1, 0), PlayerMarker.O);
        state.move(new Point(1, 1), PlayerMarker.X);
        state.move(new Point(2, 0), PlayerMarker.O);
        state.move(new Point(2, 2), PlayerMarker.X);

        assertEquals(PlayerMarker.X, state.getWinner());
    }

    @Test
    public void oWinsTheGame() {
        state.move(new Point(0, 0), PlayerMarker.X);
        state.move(new Point(1, 0), PlayerMarker.O);
        state.move(new Point(2, 0), PlayerMarker.X);
        state.move(new Point(1, 1), PlayerMarker.O);
        state.move(new Point(2, 2), PlayerMarker.X);
        state.move(new Point(1, 2), PlayerMarker.O);

        assertEquals(PlayerMarker.O, state.getWinner());
    }

    @Test
    public void moveAddsMarker() {
        state.move(new Point(1, 0), PlayerMarker.X);
        assertEquals(PlayerMarker.X, state.getBoard().get(new Point(1, 0)));
    }

    @Test
    public void gameIsOver() {
        assertEquals(false, state.isOver());

        state.move(new Point(0, 0), PlayerMarker.X);
        state.move(new Point(1, 0), PlayerMarker.X);
        state.move(new Point(2, 0), PlayerMarker.X);

        assertEquals(PlayerMarker.X, state.getWinner());
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
        state.move(new Point(0, 0), PlayerMarker.X);
        state.move(new Point(2, 0), PlayerMarker.O);
        state.move(new Point(0, 2), PlayerMarker.X);
        state.move(new Point(0, 1), PlayerMarker.O);
        state.move(new Point(1, 1), PlayerMarker.X);
        state.move(new Point(2, 2), PlayerMarker.O);
        state.move(new Point(1, 0), PlayerMarker.X);
        state.move(new Point(1, 2), PlayerMarker.O);
        state.move(new Point(2, 1), PlayerMarker.X);
    }

    @Test
    public void itCanTestEqualityWithOtherStates() {
        this.state.move(new Point(1, 0), PlayerMarker.X);

        State newState = new State();
        assertNotEquals(newState, this.state);

        newState.move(new Point(1, 0), PlayerMarker.X);
        assertEquals(newState, this.state);
    }

    @Test
    public void itCanMakeACopyOfItself() {
        assertEquals(this.state, this.state.copy());
        assertEquals(this.state.getNextTurnMarker(), this.state.copy().getNextTurnMarker());

        State newState = this.state.copy()
                .move(new Point(0, 1), PlayerMarker.X);

        assertNotEquals(this.state, newState);
        assertEquals(PlayerMarker.O, newState.getNextTurnMarker());
    }

    @Test
    public void itKnowsTheNextPlayerToMakeAMove() {
        assertEquals(PlayerMarker.X, this.state.getNextTurnMarker());

        this.state.move(new Point(0, 0), PlayerMarker.X);
        assertEquals(PlayerMarker.O, this.state.getNextTurnMarker());

        this.state.move(new Point(0, 2), PlayerMarker.O);
        assertEquals(PlayerMarker.X, this.state.getNextTurnMarker());
    }

}