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

        assertThat(writer.getOutput(), CoreMatchers.containsString("Is X a human or a computer? H/C"));
        assertThat(writer.getOutput(), CoreMatchers.containsString("Is O a human or a computer? H/C"));
        assertThat(writer.getOutput(), CoreMatchers.containsString("Who will go first? X/O"));
    }

    @Test
    public void itSetsUpAHumanVsHumanGame() {
        Configurator configurator = new Configurator(ui);
        reader.addLine("H")
                .addLine("H")
                .addLine("X");

        Game game = new Game(PlayerFactory.human(Marker.X, ui), PlayerFactory.human(Marker.O, ui));

        assertEquals(game, configurator.configure());
    }

    @Test
    public void itSetsUpAHumanVsComputerGame() {
        Configurator configurator = new Configurator(ui);
        reader.addLine("H")
                .addLine("C")
                .addLine("X");

        Game game = new Game(PlayerFactory.human(Marker.X, ui), PlayerFactory.computer(Marker.O));

        assertEquals(game, configurator.configure());
    }

    @Test
    public void itSetsUpTheGameWithThePlayerThatIsGoingFirst() {
        Configurator configurator = new Configurator(ui);
        reader.addLine("C")
                .addLine("C")
                .addLine("O");

        Game game = new Game(PlayerFactory.computer(Marker.O), PlayerFactory.computer(Marker.X));

        assertEquals(game, configurator.configure());
    }
}
