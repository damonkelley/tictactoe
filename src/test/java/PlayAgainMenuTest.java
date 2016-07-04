import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlayAgainMenuTest {
    @Test
    public void itWillDisplayTheMenu() {
        FakeUI ui = new FakeUI();
        ui.input.add("N");

        new PlayAgainMenu(ui).display();

        assertEquals("Play again?\nY/N:  ", ui.log);
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
        ui.input.add("N");
        ui.input.add("Y");

        PlayAgainMenu menu = new PlayAgainMenu(ui).display();
        assertFalse(menu.shouldPlayAgain());

        menu.display();
        assertTrue(menu.shouldPlayAgain());
    }
}
