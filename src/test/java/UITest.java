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
            " 1 | 2 | 3 \n" +
            "---+---+---\n" +
            " 4 | 5 | 6 \n" +
            "---+---+---\n" +
            " 7 | 8 | 9 \n";

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
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("1\n2\n4\n3\n7\n".getBytes())));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

        UI ui = new UI(reader, writer);

        ui.start();

        String expectedBoard =
                        " X | O | O \n" +
                        "---+---+---\n" +
                        " X | 5 | 6 \n" +
                        "---+---+---\n" +
                        " X | 8 | 9 \n";

        assertTrue(out.toString().contains(expectedBoard));
    }

}