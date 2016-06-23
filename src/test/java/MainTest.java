import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MainTest {
    @Test
    public void theGameCanBePlayed() throws IOException {
        System.setIn(new ByteArrayInputStream("1\n2\n".getBytes()));

        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[] {});
        String output = out.toString();

        assertTrue(output.contains(" O | O | 3 \n"));
        assertTrue(output.contains(" X | X | X \n"));
        assertThat(output, CoreMatchers.containsString("Game Over"));
    }
}
