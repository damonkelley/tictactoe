package me.damonkelley.ui;

import me.damonkelley.fake.FakeUI;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.PlayerFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameConfigurationMenuTest {
    private FakeUI ui;

    @Before
    public void setUp() throws Exception {
        ui = new FakeUI();
    }

    @Test
    public void itHasDefaults() {
        GameConfigurationMenu menu = new GameConfigurationMenu(ui);
        assertEquals(PlayerFactory.human(Marker.X, ui), menu.getXPlayer());
        assertEquals(PlayerFactory.computer(Marker.O), menu.getOPlayer());
        assertEquals(3, menu.getBoardSize());
        assertEquals(Marker.X, menu.getFirstMarker());
    }

    @Test
    public void itAsksTheUserHowTheGameShouldBeSetUp() {
        GameConfigurationMenu menu = new GameConfigurationMenu(ui);

        ui.input.add("H");
        ui.input.add("H");
        ui.input.add("4");
        ui.input.add("X");

        menu.display();

        String expected = "Is X a human (H) or a computer (C)?\nH/C:  " +
                          "Is O a human (H) or a computer (C)?\nH/C:  " +
                          "How large is the board? 3x3 (3) or 4x4 (4)?\n3/4:  " +
                          "Who will go first?\nX/O:  " ;

        assertEquals(expected, ui.log);
    }

    @Test
    public void itCreatesTwoHumanPlayers() {
        ui.input.add("H");
        ui.input.add("H");
        ui.input.add("3");
        ui.input.add("X");

        GameConfigurationMenu config = new GameConfigurationMenu(ui);
        config.display();

        assertEquals(PlayerFactory.human(Marker.X, ui), config.getXPlayer());
        assertEquals(PlayerFactory.human(Marker.O, ui), config.getOPlayer());
        assertEquals(Marker.X, config.getFirstMarker());
    }

    @Test
    public void itCreatesAHumanAndComputerPlayer() {
        ui.input.add("H");
        ui.input.add("C");
        ui.input.add("3");
        ui.input.add("X");

        GameConfigurationMenu config = new GameConfigurationMenu(ui);
        config.display();

        assertEquals(PlayerFactory.human(Marker.X, ui), config.getXPlayer());
        assertEquals(PlayerFactory.computer(Marker.O), config.getOPlayer());
        assertEquals(Marker.X, config.getFirstMarker());
    }

    @Test
    public void itCollectsTheBoardSizeOption() {
        ui.input.add("C");
        ui.input.add("C");
        ui.input.add("4");
        ui.input.add("O");

        GameConfigurationMenu config = new GameConfigurationMenu(ui);
        config.display();

        assertEquals(4, config.getBoardSize());
    }

    @Test
    public void itWillOnlyAcceptABoardSizeOf3or4() {
        ui.input.add("C");
        ui.input.add("C");

        ui.input.add("5");
        ui.input.add("8");
        ui.input.add("1");
        ui.input.add("2");
        ui.input.add("3");

        ui.input.add("O");

        GameConfigurationMenu config = new GameConfigurationMenu(ui);
        config.display();

        assertEquals(3, config.getBoardSize());
    }

    @Test
    public void itCreatesTwoComputerPlayers() {
        ui.input.add("C");
        ui.input.add("C");
        ui.input.add("3");
        ui.input.add("O");

        GameConfigurationMenu config = new GameConfigurationMenu(ui);
        config.display();

        assertEquals(PlayerFactory.computer(Marker.X), config.getXPlayer());
        assertEquals(PlayerFactory.computer(Marker.O), config.getOPlayer());
        assertEquals(Marker.O, config.getFirstMarker());
    }

}
