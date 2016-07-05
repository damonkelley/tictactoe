import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ConfiguratorTest {
    private FakeReader reader;
    private UI ui;
    private FakeWriter writer;

    @Before
    public void setUp() throws Exception {
        reader = new FakeReader();
        writer = new FakeWriter();
        ui = new UI(reader, writer);
    }

    @Test
    public void itAsksTheUserHowTheGameShouldBeSetUp() {
        Configurator configurator = new Configurator(ui);

        reader.addLine("H")
            .addLine("H")
            .addLine("X");

        configurator.configure();

        assertThat(writer.getOutput(), CoreMatchers.containsString("Is O a human (H) or a computer (C)?\nH/C: "));
        assertThat(writer.getOutput(), CoreMatchers.containsString("Who will go first?\nX/O: "));
    }

    @Test
    public void itSetsUpAHumanVsHumanGame() {
        reader.addLine("H")
                .addLine("H")
                .addLine("X");

        Game game = new Game(PlayerFactory.human(Marker.X, ui), PlayerFactory.human(Marker.O, ui));

        assertEquals(game, new Configurator(ui).configure());
    }

    @Test
    public void itSetsUpAHumanVsComputerGame() {
        reader.addLine("H")
                .addLine("C")
                .addLine("X");

        Game game = new Game(PlayerFactory.human(Marker.X, ui), PlayerFactory.computer(Marker.O));

        assertEquals(game, new Configurator(ui).configure());
    }

    @Test
    public void itSetsUpTheGameWithThePlayerThatIsGoingFirst() {
        reader.addLine("C")
                .addLine("C")
                .addLine("O");

        Game game = new Game(PlayerFactory.computer(Marker.O), PlayerFactory.computer(Marker.X));

        assertEquals(game, new Configurator(ui).configure());
    }
}
