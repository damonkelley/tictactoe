import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApplicationTest {

    private FakeUI ui;
    private FakeGameConfigurationMenu configMenu;
    private FakePlayAgainMenu playAgainMenu;
    private FakeGameLoop gameLoop;
    String log;

    @Before
    public void setUp() throws Exception {
        ui = new FakeUI();

        configMenu = new FakeGameConfigurationMenu(ui);
        playAgainMenu = new FakePlayAgainMenu(ui);
        gameLoop = new FakeGameLoop(ui);

        log = "";
    }

    @Test
    public void itPlaysTheGame() {
        new Application(configMenu, gameLoop, playAgainMenu, ui).start();
        assertEquals("config-menu play play-again-menu ", log);
    }

    @Test
    public void itWillPlayTheGameAgainWithTheSameConfiguration() {
        playAgainMenu.iterations++;

        new Application(configMenu, gameLoop, playAgainMenu, ui).start();
        assertEquals("config-menu play play-again-menu reset play play-again-menu ", log);
    }

    @Test
    public void itCanBeReconfiguredAfterAGameHasBeenPlayed() {
        playAgainMenu.iterations++;
        playAgainMenu.reconfigure = true;

        new Application(configMenu, gameLoop, playAgainMenu, ui).start();
        assertEquals("config-menu play play-again-menu config-menu play play-again-menu ", log);
    }

    @Test
    public void itWillExitGracefullyIfTheGameIsQuitDuringConfiguration() {
        configMenu = new FakeGameConfigurationMenu(ui) {
            @Override
            public FakeGameConfigurationMenu display() {
                super.display();
                throw new GameException("Goodbye");
            }
        };

        new Application(configMenu, gameLoop, playAgainMenu, ui).start();

        assertEquals("config-menu ", this.log);
        assertEquals("Goodbye ", ui.log);
    }

    @Test
    public void itWillExitGracefullyIfTheGameIsQuit() {
        gameLoop = new FakeGameLoop(ui) {
            @Override
            public void play(Game game) {
                log += "play ";
                throw new GameException("Goodbye");
            }
        };

        new Application(configMenu, gameLoop, playAgainMenu, ui).start();

        assertEquals("config-menu play ", this.log);
        assertEquals("Goodbye ", ui.log);
    }

    private class FakeGameConfigurationMenu extends GameConfigurationMenu {
        public FakeGameConfigurationMenu(UI ui) {
            super(ui);
        }

        @Override
        public GameConfigurationMenu display() {
            log += "config-menu ";
            return this;
        }
    }

    private class FakePlayAgainMenu extends PlayAgainMenu {
        public int iterations = 1;
        public boolean reconfigure = false;

        public FakePlayAgainMenu(UI ui) {
            super(ui);
        }

        @Override
        public PlayAgainMenu display() {
            log += "play-again-menu ";
            iterations--;
            return this;
        }

        @Override
        public boolean shouldPlayAgain() {
            return iterations > 0;
        }

        @Override
        public boolean shouldReconfigure() {
            return reconfigure;
        }
    }

    private class FakeGameLoop extends GameLoop {
        public FakeGameLoop(UI ui) {
            super(ui);
        }

        @Override
        public void play(Game game) {
            log += "play ";
        }

        @Override
        public void reset(Game game) {
            log += "reset ";
            super.reset(game);
        }
    }
}
