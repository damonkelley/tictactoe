import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class MainTest {
    @Test
    public void theGameCanBePlayed() throws IOException {
        System.setIn(new ByteArrayInputStream("1\n2\n8\n".getBytes()));

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
}
