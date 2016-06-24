import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class SpaceTest {

    @Test
    public void itCanBeComparedWithOtherSpaces() {
        assertEquals(0, new Space(0, 0).compareTo(new Space(0, 0)));
        assertEquals(1, new Space(2, 2).compareTo(new Space(0, 0)));
        assertEquals(-1, new Space(0, 1).compareTo(new Space(0, 2)));
        assertEquals(-1, new Space(1, 1).compareTo(new Space(0, 2)));
        assertEquals(-1, new Space(1, 1).compareTo(new Space(2, 2)));
    }

}
