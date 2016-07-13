package me.damonkelley.tictactoe;

import me.damonkelley.io.validators.InputValidationError;
import me.damonkelley.ui.UI;

public class GameLoop {
    private UI ui;

    public GameLoop(UI ui) {
        this.ui = ui;
    }

    public void play(Players players, Game game) {
        ui.render(game);
        while (!game.isOver()) {
            nextTurn(players.get(game.nextTurn()), game);
        }
        ui.message("Game Over");
    }

    private void nextTurn(Player player, Game game) {
        try {
            player.move(game);
            ui.render(game);
        } catch (InputValidationError e) {
            ui.message(e.getMessage());
        }
    }

    public void reset(Game game) {
        game.reset();
    }
}
