public class Application {
    private GameConfigurationMenu configMenu;
    private final GameLoop gameLoop;
    private final PlayAgainMenu playAgainMenu;
    private final UI ui;
    private Game game;

    public Application(GameConfigurationMenu configMenu, GameLoop gameLoop, PlayAgainMenu playAgainMenu, UI ui) {
        this.configMenu = configMenu;
        this.gameLoop = gameLoop;
        this.playAgainMenu = playAgainMenu;
        this.ui = ui;
    }


    public void start() {
        try {
            newGame();
        } catch (GameException e) {
            ui.message(e.getMessage());
        }
    }

    private void newGame() {
        configMenu.display();
        game = new GameBuilder()
                .setXPlayer(configMenu.getXPlayer())
                .setOPlayer(configMenu.getOPlayer())
                .setFirstMarker(configMenu.getFirstMarker())
                .build();
        play();
    }

    private boolean playAgain() {
        playAgainMenu.display();

        if (playAgainMenu.shouldPlayAgain()) {
            gameLoop.reset(game);
            return true;
        }
        return false;
    }

    private void play() {
        do {
            gameLoop.play(game);
        } while (playAgain());
    }
}
