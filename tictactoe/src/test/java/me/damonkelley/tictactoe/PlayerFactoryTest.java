package me.damonkelley.tictactoe;

import me.damonkelley.fake.FakeReader;
import me.damonkelley.fake.FakeWriter;
import me.damonkelley.tictactoe.finder.ArtificialIntelligenceFinder;
import me.damonkelley.tictactoe.finder.HumanFinder;
import me.damonkelley.ui.UI;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerFactoryTest {
    @Test
    public void itCanCreateAComputerPlayer() {
        assertEquals(new Player(Marker.X, new ArtificialIntelligenceFinder(Marker.X)), PlayerFactory.computer(Marker.X));
        assertEquals(new Player(Marker.O, new ArtificialIntelligenceFinder(Marker.O)), PlayerFactory.computer(Marker.O));
    }

    @Test
    public void itCanCreateAHumanPlayer() {
        UI ui = new UI(new FakeReader(), new FakeWriter());

        assertEquals(new Player(Marker.X, new HumanFinder(ui)), PlayerFactory.human(Marker.X, ui));
        assertEquals(new Player(Marker.O, new HumanFinder(ui)), PlayerFactory.human(Marker.O, ui));
    }
}
