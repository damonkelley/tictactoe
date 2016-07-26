package me.damonkelley.tictactoe;

import me.damonkelley.fake.FakeUI;
import me.damonkelley.fake.NullFinder;
import me.damonkelley.io.validators.InputValidationError;
import me.damonkelley.tictactoe.finder.Finder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameLoopTest {

    @Test
    public void itPlaysTheGame() {
        FakeUI ui = new FakeUI();
        Game game = new FakeGame(3);
        Players players = new Players(new Player(Marker.O, new NullFinder()), new Player(Marker.X, new NullFinder()));

        new GameLoop(ui).play(players, game);

        assertEquals("render render render render Game Over ", ui.log);
    }

    @Test
    public void itLogsValidationErrorsToTheUI() {
        Game game = new FakeGame(3);
        FakeUI ui = new FakeUI();

        class ExceptionalFinder extends Finder {
            @Override
            public Space getNextMove(Game game) {
                throw new InputValidationError("Bad Argument");
            }
        }

        Players players = new Players(
                new Player(Marker.O, new ExceptionalFinder()),
                new Player(Marker.X, new ExceptionalFinder())
        );

        new GameLoop(ui).play(players, game);

        assertEquals("render Bad Argument Bad Argument Bad Argument Game Over ", ui.log);
    }

    @Test
    public void itLogsIllegalMovesAttemptedToTheUI() {
        Game game = new FakeGame(2);
        FakeUI ui = new FakeUI();

        class ExceptionalFinder extends Finder {
            @Override
            public Space getNextMove(Game game) {
                throw new IllegalMoveException("Bad move");
            }
        }

        Players players = new Players(
                new Player(Marker.O, new ExceptionalFinder()),
                new Player(Marker.X, new ExceptionalFinder())
        );

        new GameLoop(ui).play(players, game);

        assertEquals("render Bad move Bad move Game Over ", ui.log);
    }

    @Test
    public void itCanResetTheGame() {
        FakeUI ui = new FakeUI();
        FakeGame game = new FakeGame(1);
        Players players = new Players(new Player(Marker.O, new NullFinder()), new Player(Marker.X, new NullFinder()));

        GameLoop gameLoop = new GameLoop(ui);

        gameLoop.play(players, game);
        gameLoop.reset(game);
        gameLoop.play(players, game);

        assertEquals("render render Game Over render render Game Over ", ui.log);
    }

    private class FakeGame extends Game {
        private int iterations;
        private int currentIteration;

        public FakeGame(int iterations) {
            super(Marker.X);
            this.currentIteration = this.iterations = iterations;
        }

        @Override
        public Game move(Space space, Marker marker) {
            return this;
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
