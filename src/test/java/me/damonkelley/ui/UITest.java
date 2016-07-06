package me.damonkelley.ui;

import me.damonkelley.fake.FakeReader;
import me.damonkelley.fake.FakeWriter;
import me.damonkelley.io.validators.Validator;
import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.GameException;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Player;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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

        assertEquals("2", ui.getUserInput());
        assertEquals("3", ui.getUserInput());
    }

    @Test
    public void itWritesToOut() throws IOException {
        Game game = new Game(new Player(Marker.O, null), new Player(Marker.X, null));
        new UI(reader, writer).render(game);

        String expected =
                "\u001B[2J\u001B[H" +
                        " 1 | 2 | 3 \n" +
                        "---+---+---\n" +
                        " 4 | 5 | 6 \n" +
                        "---+---+---\n" +
                        " 7 | 8 | 9 \n" +
                        "\n";

        Assert.assertEquals(expected, writer.getOutput());
    }

    @Test(expected = GameException.class)
    public void itThrowsAnExceptionIfEndOfFileIsReached() {
        String EOF = null;
        reader.addLine(EOF);

        UI ui = new UI(reader, writer);
        ui.getUserInput();
    }

    @Test
    public void itCanSendAMessageToTheUser() {
        new UI(reader, writer).message("Hello world!");
        assertThat(writer.getOutput(), CoreMatchers.containsString("Hello world!\n"));
    }

    @Test
    public void itPromptsTheUserForInput() {
        UI ui = new UI(reader, writer);
        Validator validator = new Validator() {
            @Override
            public boolean isValid(String input) {
                return true;
            }
        };

        reader.addLine("yes");

        assertEquals("yes", ui.prompt("Did I prompt you?", validator));
        assertThat(writer.getOutput(), CoreMatchers.containsString("Did I prompt you?"));
    }

    @Test
    public void itWillContinueToPromptUntilItGetsValidInput() {
        UI ui = new UI(reader, writer);
        Validator validator = new Validator() {
            @Override
            public boolean isValid(String input) {
                if ("yes".equals(input)) {
                    return true;
                }
                return false;
            }
        };

        reader.addLine("no")
                .addLine("yes");

        assertEquals("yes", ui.prompt("prompt?", validator));
        assertThat(writer.getOutput(), CoreMatchers.containsString("prompt?prompt?"));
    }
}
