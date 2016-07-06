package me.damonkelley.ui;

import me.damonkelley.fake.FakeUI;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlayAgainMenuTest {
    @Test
    public void itWillDisplayTheMenu() {
        FakeUI ui = new FakeUI();
        ui.input.add("Y");
        ui.input.add("N");

        new PlayAgainMenu(ui).display();

        assertEquals("Play again?\nY/N:  Do you want to use a different game configuration?\nY/N:  ", ui.log);
    }

    @Test
    public void itWillOnlyAcceptAcceptYOrN() {
        FakeUI ui = new FakeUI();
        ui.input.add("asdk");
        ui.input.add("0091");
        ui.input.add("N");

        new PlayAgainMenu(ui).display();

        assertEquals("Play again?\nY/N:  Play again?\nY/N:  Play again?\nY/N:  ", ui.log);
    }

    @Test
    public void itDeterminesIfTheGameShouldBePlayedAgain() {
        FakeUI ui = new FakeUI();
        ui.input.add("Y");
        ui.input.add("Y");
        ui.input.add("Y");
        ui.input.add("N");

        PlayAgainMenu menu = new PlayAgainMenu(ui).display();
        assertTrue(menu.shouldPlayAgain());
        assertTrue(menu.shouldReconfigure());

        menu.display();
        assertTrue(menu.shouldPlayAgain());
        assertFalse(menu.shouldReconfigure());
    }
}
