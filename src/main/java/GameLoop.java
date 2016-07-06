public class GameLoop {
    private UI ui;

    public GameLoop(UI ui) {
        this.ui = ui;
    }

    public void play(Game game) {
        ui.render(game);
        while (!game.isOver()) {
            nextTurn(game);
        }
        ui.message("Game Over");
    }

    private void nextTurn(Game game) {
        try {
            game.nextMove();
            ui.render(game);
        } catch (InputValidationError e) {
            ui.message(e.getMessage());
        }
    }

    public void reset(Game game) {
        game.reset();
    }
}
