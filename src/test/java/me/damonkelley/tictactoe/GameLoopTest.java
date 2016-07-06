package me.damonkelley.tictactoe;

import me.damonkelley.io.validators.InputValidationError;
import me.damonkelley.ui.UI;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameLoopTest {

    @Test
    public void itPlaysTheGame() {
        FakeUI ui = new FakeUI();
        Game game = new FakeGame(3);

        GameLoop loop = new GameLoop(ui);
        loop.play(game);

        assertEquals("render render render render Game Over ", ui.log);
    }

    @Test
    public void itLogsValidationErrorsToTheUI() {
        Game game = new FakeGame(3) {
            @Override
            public void nextMove() {
                throw new InputValidationError("Bad Argument");
            }
        };
        FakeUI ui = new FakeUI();

        new GameLoop(ui).play(game);

        assertEquals("render Bad Argument Bad Argument Bad Argument Game Over ", ui.log);
    }

    @Test
    public void itCanResetTheGame() {
        FakeUI ui = new FakeUI();
        FakeGame game = new FakeGame(1);

        GameLoop gameLoop = new GameLoop(ui);
        gameLoop.play(game);
        gameLoop.reset(game);
        gameLoop.play(game);

        assertEquals("render render Game Over render render Game Over ", ui.log);

    }

    private class FakeGame extends Game {
        private int iterations;
        private int currentIteration;

        public FakeGame(int iterations) {
            super(new Player(Marker.X, null), new Player(Marker.O, null));
            this.currentIteration = this.iterations = iterations;
        }

        @Override
        public void nextMove() {
        }

        @Override
        public boolean isOver() {
            if (currentIteration == 0) {
                return true;
            }
            currentIteration--;
            return false;
        }

        @Override
        public Game reset() {
            currentIteration = iterations;
            return this;
        }
    }
}
