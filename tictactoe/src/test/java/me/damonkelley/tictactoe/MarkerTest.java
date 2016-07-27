package me.damonkelley.tictactoe;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MarkerTest {
    @Test
    public void itCanGetTheOppositeMarker() {
        assertEquals(Marker.O, Marker.X.opposite());
        assertEquals(Marker.X, Marker.O.opposite());
    }
}
