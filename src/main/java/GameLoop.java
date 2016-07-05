public class GameLoop {
    private Game game;
    private UI ui;

    public GameLoop(Game game, UI ui) {
        this.game = game;
        this.ui = ui;
    }

    public void play() {
        ui.render(game);
        while (!game.isOver()) {
            nextTurn();
        }
        ui.message("Game Over");
    }

    private void nextTurn() {
        try {
            game.nextMove();
            ui.render(game);
        } catch (InputValidationError e) {
            ui.message(e.getMessage());
        }
    }
}
