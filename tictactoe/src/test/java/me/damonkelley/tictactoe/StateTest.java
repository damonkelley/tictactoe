package me.damonkelley.tictactoe;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class StateTest {
    private State state;

    @Before
    public void setUp() throws Exception {
        this.state = new State();
    }

    @Test
    public void moveAddsMarker() {
        state.move(new Space(1, 0), Marker.X);
        assertEquals(Marker.X, state.getBoard().get(new Space(1, 0)));
    }

    @Test
    public void itCanTestEqualityWithOtherStates() {
        this.state.move(new Space(1, 0), Marker.X);

        State newState = new State();
        assertNotEquals(newState, this.state);

        newState.move(new Space(1, 0), Marker.X);
        assertEquals(newState, this.state);
    }

    @Test
    public void itCanMakeACopyOfItself() {
        assertEquals(this.state, this.state.copy());
        assertEquals(this.state.getNextMarker(), this.state.copy().getNextMarker());

        State newState = this.state.copy()
                .move(new Space(0, 1), Marker.X);

        assertNotEquals(this.state, newState);
        assertEquals(Marker.O, newState.getNextMarker());
    }

    @Test
    public void itKnowsTheNextPlayerToMakeAMove() {
        assertEquals(Marker.X, this.state.getNextMarker());

        this.state.move(new Space(0, 0), Marker.X);
        assertEquals(Marker.O, this.state.getNextMarker());

        this.state.move(new Space(0, 2), Marker.O);
        assertEquals(Marker.X, this.state.getNextMarker());
    }

    @Test
    public void itIsConfiguredWithTheFirstMarkerToMove() {
        assertEquals(Marker.O, new State(Marker.O).getNextMarker());
        assertEquals(Marker.X, new State(Marker.X).getNextMarker());
    }

}