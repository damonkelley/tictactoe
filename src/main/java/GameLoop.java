public class GameLoop {
    private Game game;
    private UI ui;

    public GameLoop(Game game, UI ui) {
        this.game = game;
        this.ui = ui;
    }

    public void play() {
        ui.render();
        while (!game.isOver()) {
            game.nextMove();
            ui.render();
        }
        ui.message("Game Over");
    }
}
