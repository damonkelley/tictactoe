import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class UITest {
    private FakeWriter writer;
    private FakeReader reader;

    @Before
    public void setUp() throws Exception {
        reader = new FakeReader();
        writer = new FakeWriter();
    }

    @Test
    public void itReadsFromIn() throws IOException {
        reader.addLine("2")
            .addLine("3");

        UI ui = new UI(reader, writer);
        Game game = new Game(new Player(Marker.O, ui), new Player(Marker.X, ui));

        assertEquals(new Space(1, 0), ui.getNextMove(game));
        assertEquals(new Space(2, 0), ui.getNextMove(game));
    }

    @Test
    public void itCanHandleNonNumericCharacters() {
        reader.addLine("asdf")
                .addLine("1");

        UI ui = new UI(reader, writer);
        Game game = new Game(new Player(Marker.O, ui), new Player(Marker.X, ui));

        assertEquals(new Space(0, 0), ui.getNextMove(game));
        assertThat(writer.getOutput(), CoreMatchers.containsString("asdf is not a valid space\n"));
    }

    @Test
    public void itCanHandleSpaceInvalidSpaceIds() {
        reader.addLine("1000")
                .addLine("2");

        UI ui = new UI(reader, writer);
        Game game = new Game(new Player(Marker.O, ui), new Player(Marker.X, ui));

        assertEquals(new Space(1, 0), ui.getNextMove(game));
        assertThat(writer.getOutput(), CoreMatchers.containsString("1000 is not a valid space\n"));
    }

    @Test
    public void itWritesToOut() throws IOException {
        reader.addLine("3");

        new UI(reader, writer).render();

        String expected =
                "\u001B[2J\u001B[H" +
                " 1 | 2 | 3 \n" +
                "---+---+---\n" +
                " 4 | 5 | 6 \n" +
                "---+---+---\n" +
                " 7 | 8 | 9 \n" +
                "\n";

        assertEquals(expected, writer.getOutput());
    }

    @Test
    public void itCanConvertASpaceIdToAPoint() throws IOException {
        reader.addLine("1")
            .addLine("9");

        UI ui = new UI(reader, writer);
        Game game = new Game(new Player(Marker.O, ui), new Player(Marker.X, ui));

        assertEquals(new Space(0, 0), ui.getNextMove(game));
        assertEquals(new Space(2, 2), ui.getNextMove(game));
    }

    @Test
    public void itCanSendAMessageToTheUser() {
        new UI(reader, writer).message("Hello world!");
        assertThat(writer.getOutput(), CoreMatchers.containsString("Hello world!\n"));
    }

    @Test
    public void itStartsTheGame() throws IOException {
        reader.addLine("1")
            .addLine("2")
            .addLine("4");

        new UI(reader, writer).start();

        String expectedBoard =
                " O | O | X \n" +
                "---+---+---\n" +
                " O | X | 6 \n" +
                "---+---+---\n" +
                " X | 8 | 9 \n";

        assertTrue(writer.getOutput().contains(expectedBoard));
        assertTrue(writer.getOutput().contains("Game Over"));
    }

    private class FakeReader extends BufferedReader {

        private Deque<String> lines = new ArrayDeque<>();

        public FakeReader() {
            super(new Reader() {
                @Override
                public int read(char[] cbuf, int off, int len) throws IOException {return 0;}

                @Override
                public void close() throws IOException {}
            });
        }

        public FakeReader addLine(String line) {
            lines.add(line);
            return this;
        }

        @Override
        public String readLine() throws IOException {
            return lines.remove();
        }
    }

    private class FakeWriter extends BufferedWriter {

        private StringBuffer out = new StringBuffer();

        public FakeWriter() {
            super(new Writer() {
                @Override
                public void write(char[] cbuf, int off, int len) throws IOException {}

                @Override
                public void flush() throws IOException {}

                @Override
                public void close() throws IOException {}
            });
        }

        @Override
        public void write(String str) throws IOException {
            out.append(str);
        }

        public String getOutput() {
            return out.toString();
        }
    }
}
