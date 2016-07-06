package me.damonkelley;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.GameBuilder;
import me.damonkelley.tictactoe.GameException;
import me.damonkelley.tictactoe.GameLoop;
import me.damonkelley.tictactoe.Players;
import me.damonkelley.ui.GameConfigurationMenu;
import me.damonkelley.ui.PlayAgainMenu;
import me.damonkelley.ui.UI;

public class Application {
    private final GameConfigurationMenu configMenu;
    private final GameLoop gameLoop;
    private final PlayAgainMenu playAgainMenu;
    private final UI ui;

    private Game game;
    private Players players;

    public Application(GameConfigurationMenu configMenu, GameLoop gameLoop, PlayAgainMenu playAgainMenu, UI ui) {
        this.configMenu = configMenu;
        this.gameLoop = gameLoop;
        this.playAgainMenu = playAgainMenu;
        this.ui = ui;
    }

    public void start() {
        try {
            newGame();
            play();
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

        players = new Players(configMenu.getOPlayer(), configMenu.getXPlayer());
    }

    private void play() {
        do gameLoop.play(players, game); while (playAgain());
    }

    private boolean playAgain() {
        playAgainMenu.display();
        boolean shouldPlayAgain = playAgainMenu.shouldPlayAgain();

        if (shouldPlayAgain) reset();

        return shouldPlayAgain;
    }

    private void reset() {
        if (playAgainMenu.shouldReconfigure()) {
            newGame();
        } else {
            gameLoop.reset(game);
        }
    }
}
