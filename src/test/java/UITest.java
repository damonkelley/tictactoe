import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UITest {
    private OutputStream out;

    @Before
    public void setUp() throws Exception {
        out = new ByteArrayOutputStream();
    }

    @Test
    public void itReadsFromIn() throws IOException {
        BufferedWriter writer = makeWriter(out);

        UI ui = new UI(makeReaderWithInput("2\n"), writer);
        assertEquals(new Point(0, 1), ui.getNextMove());

        ui = new UI(makeReaderWithInput("3\n"), writer);
        assertEquals(new Point(0, 2), ui.getNextMove());
    }

    @Test
    public void itWritesToOut() throws IOException {
        BufferedReader reader = makeReaderWithInput("3\n");
        BufferedWriter writer = makeWriter(out);
        UI ui = new UI(reader, writer);

        ui.render();

        String expected =
            "\u001B[2J\u001B[H" +
            " 1 | 2 | 3 \n" +
            "---+---+---\n" +
            " 4 | 5 | 6 \n" +
            "---+---+---\n" +
            " 7 | 8 | 9 \n" +
            "\n";

        assertEquals(expected, out.toString());
    }

    @Test
    public void itCanConvertASpaceIndexToAPoint() throws IOException {
        BufferedReader reader = makeReaderWithInput("1\n9\n");
        BufferedWriter writer = makeWriter(out);

        UI ui = new UI(reader, writer);

        assertEquals(new Point(0, 0), ui.getNextMove());
        assertEquals(new Point(2, 2), ui.getNextMove());

    }

    @Test
    public void itStartsTheGame() throws IOException {
        BufferedWriter writer = makeWriter(out);
        BufferedReader reader = makeReaderWithInput("1\n2\n4\n");

        UI ui = new UI(reader, writer);

        ui.start();

        String expectedBoard =
                        " O | O | X \n" +
                        "---+---+---\n" +
                        " O | X | 6 \n" +
                        "---+---+---\n" +
                        " X | 8 | 9 \n";

        assertTrue(out.toString().contains(expectedBoard));
        assertTrue(out.toString().contains("Game Over"));
    }

    private BufferedReader makeReaderWithInput(String input) {
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
    }

    private BufferedWriter makeWriter(OutputStream out) {
        return new BufferedWriter(new OutputStreamWriter(out));
    }

}
