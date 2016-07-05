package me.damonkelley;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class MainTest {
    @Test
    public void theGameCanBeConfiguredAsHumanVsHuman() throws IOException {
        System.setIn(new ByteArrayInputStream("H\nH\nX\n1\n4\n2\n5\n3\nN\n".getBytes()));

        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

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
        System.setIn(new ByteArrayInputStream("C\nH\nO\n1\n2\n8\nN\n".getBytes()));

        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

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
    public void itExitsGracefullyIfAGameExceptionIsThrown() throws IOException {
        System.setIn(new ByteArrayInputStream(new byte[] {Byte.parseByte("0", 2)}));

        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[] {});

        String output = out.toString();
        assertTrue(output.contains("Goodbye"));
    }

    @Test
    public void itWillPlayAgain() throws IOException {
        System.setIn(new ByteArrayInputStream("C\nC\nO\nY\nN\n".getBytes()));

        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[] {});

        String output = out.toString();
        assertTrue(output.contains("Play again?"));
    }
}
