import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpaceIDConverterTest {
    @Test
    public void itConvertsAnIntegerToASpace() {
        SpaceIDConverter converter = new SpaceIDConverter(3, 3);
        assertEquals(new Space(0, 0), converter.convert(1));
        assertEquals(new Space(1, 0), converter.convert(2));
        assertEquals(new Space(2, 0), converter.convert(3));
        assertEquals(new Space(0, 1), converter.convert(4));
        assertEquals(new Space(1, 1), converter.convert(5));
        assertEquals(new Space(2, 1), converter.convert(6));
        assertEquals(new Space(0, 2), converter.convert(7));
        assertEquals(new Space(1, 2), converter.convert(8));
        assertEquals(new Space(2, 2), converter.convert(9));
    }
}
