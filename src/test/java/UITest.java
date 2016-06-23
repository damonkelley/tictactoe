import org.junit.Test;

import java.awt.*;
import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UITest {
    @Test
    public void itReadsFromIn() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("2\n".getBytes())));

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new ByteArrayOutputStream()));

        UI ui = new UI(reader, writer);

        assertEquals(new Point(0, 1), ui.getNextMove());

        reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("3\n".getBytes())));
        ui = new UI(reader, writer);

        assertEquals(new Point(0, 2), ui.getNextMove());
    }

    @Test
    public void itWritesToOut() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("3\n".getBytes())));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

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
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("1\n9\n".getBytes())));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

        UI ui = new UI(reader, writer);

        assertEquals(new Point(0, 0), ui.getNextMove());
        assertEquals(new Point(2, 2), ui.getNextMove());

    }

    @Test
    public void itStartsTheGame() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("1\n2\n".getBytes())));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

        UI ui = new UI(reader, writer);

        ui.start();

        String expectedBoard =
                        " O | O | 3 \n" +
                        "---+---+---\n" +
                        " 4 | 5 | 6 \n" +
                        "---+---+---\n" +
                        " X | X | X \n";

        assertTrue(out.toString().contains(expectedBoard));
        assertTrue(out.toString().contains("Game Over"));
    }

}
