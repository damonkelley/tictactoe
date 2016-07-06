package me.damonkelley.tictactoe.finder;

import me.damonkelley.fake.FakeReader;
import me.damonkelley.fake.FakeWriter;
import me.damonkelley.io.validators.InputValidationError;
import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Player;
import me.damonkelley.tictactoe.Space;
import me.damonkelley.ui.UI;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class HumanFinderTest {

    private FakeReader reader;
    private Game game;
    private UI ui;
    private FakeWriter writer;

    @Before
    public void setUp() throws Exception {
        reader = new FakeReader();
        writer = new FakeWriter();
        ui = new UI(reader, writer);

        game = new Game(Marker.O);
    }

    @Test
    public void itGetsMovesFromStdIn() {
        reader.addLine("2")
                .addLine("3");

        Finder finder = new HumanFinder(ui);

        Assert.assertEquals(new Space(1, 0), finder.getNextMove(game));
        assertEquals(new Space(2, 0), finder.getNextMove(game));
    }

    @Test
    public void itPromptsTheUserForTheNextMove() {
        reader.addLine("2")
                .addLine("3");

        Finder finder = new HumanFinder(ui);

        finder.getNextMove(game);

        assertThat(writer.getOutput(), CoreMatchers.containsString("Space #: "));
    }

    @Test(expected = InputValidationError.class)
    public void itCanHandleNonNumericCharacters() {
        reader.addLine("asdf")
                .addLine("1");

        Finder finder = new HumanFinder(ui);

        assertEquals(new Space(0, 0), finder.getNextMove(game));
    }

    @Test(expected = InputValidationError.class)
    public void itCanHandleSpaceInvalidSpaceIds() {
        reader.addLine("1000")
                .addLine("2");

        Finder finder = new HumanFinder(ui);

        assertEquals(new Space(1, 0), finder.getNextMove(game));
    }

    @Test
    public void allInstancesAreEqual() {
        assertEquals(new HumanFinder(ui), new HumanFinder(null));
        assertEquals(new HumanFinder(null), new HumanFinder(null));
    }
}
