package me.damonkelley;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;

public class MainTest {

    private ByteArrayOutputStream out;

    @Before
    public void setUp() throws Exception {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void theGameCanBeConfiguredAsHumanVsHuman() throws IOException {
        setInput(asList("3", "H", "H", "X", "1", "4", "2", "5", "3", "N"));

        Main.main(new String[] {});

        String expectedOutcome =  " X | X | X \n" +
                                  "---+---+---\n" +
                                  " O | O | 6 \n" +
                                  "---+---+---\n" +
                                  " 7 | 8 | 9 \n";

        String output = out.toString();
        assertTrue(output.contains(expectedOutcome));
        assertTrue(output.contains("Game Over"));
    }

    @Test
    public void theGameCanBePlayedAsHumanVsComputer() throws IOException {
        setInput(asList("3", "C", "H", "O", "1", "2", "8", "N"));

        Main.main(new String[] {});

        String expectedOutcome =  " O | O | X \n" +
                                  "---+---+---\n" +
                                  " 4 | X | 6 \n" +
                                  "---+---+---\n" +
                                  " X | O | 9 \n";

        String output = out.toString();
        assertTrue(output.contains(expectedOutcome));
        assertTrue(output.contains("Game Over"));
    }

    @Test
    public void theGameCanPlayAWithA4By4Board() throws IOException {
        setInput(asList("4", "C", "C", "O", "N"));

        Main.main(new String[] {});

        String expected = "  1  |  2  |  3  |  4  \n" +
                          "-----+-----+-----+-----\n" +
                          "  5  |  6  |  7  |  8  \n"  +
                          "-----+-----+-----+-----\n" +
                          "  9  |  10 |  11 |  12 \n"  +
                          "-----+-----+-----+-----\n" +
                          "  13 |  14 |  15 |  16 \n"  +
                          "\n";

        String output = out.toString();
        assertTrue(output.contains(expected));
        assertTrue(output.contains("Game Over"));
    }

    @Test
    public void itExitsGracefullyIfAGameExceptionIsThrown() throws IOException {
        setNullInput();
        Main.main(new String[] {});

        String output = out.toString();
        assertTrue(output.contains("Goodbye"));
    }

    @Test
    public void itWillPlayAgain() throws IOException {
        setInput(asList("3", "C", "C", "O", "Y", "N"));

        Main.main(new String[] {});

        String output = out.toString();
        assertTrue(output.contains("Play again?"));
    }

    private void setNullInput() {
        System.setIn(new ByteArrayInputStream(new byte[] {Byte.parseByte("0", 2)}));
    }

    private void setInput(List<String> input) {
        System.setIn(new ByteArrayInputStream(String.join("\n", input).getBytes()));
    }
}
