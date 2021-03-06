package me.damonkelley.tictactoe;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SpaceTest {

    @Test
    public void itCanBeComparedWithOtherSpaces() {
        assertEquals(0, new Space(0, 0).compareTo(new Space(0, 0)));
        assertEquals(1, new Space(2, 2).compareTo(new Space(0, 0)));
        assertEquals(-1, new Space(0, 1).compareTo(new Space(0, 2)));
        assertEquals(-1, new Space(1, 1).compareTo(new Space(0, 2)));
        assertEquals(-1, new Space(1, 1).compareTo(new Space(2, 2)));
    }

    @Test
    public void equality() {
        assertEquals(new Space(0, 0), new Space(0, 0));
        assertEquals(new Space(1, 0), new Space(1, 0));
        assertEquals(new Space(0, 1), new Space(0, 1));

        assertNotEquals(new Space(0, 0), new Space(1, 0));
        assertNotEquals(new Space(0, 0), new Space(0, 1));
        assertNotEquals(new Space(1, 0), new Space(0, 1));
        assertNotEquals(new Space(0, 1), new Space(1, 0));
    }
}
