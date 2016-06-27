import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameLoopTest {

    @Test
    public void itPlaysTheGame() {
        FakeUI ui = new FakeUI();
        Game game = new FakeGame(3);

        GameLoop loop = new GameLoop(game, ui);
        loop.play();

        assertEquals("render render render render Game Over", ui.log);
    }

    private class FakeGame extends Game {
        private int iterations;

        public FakeGame(int iterations) {
            super(new Player(Marker.X, null), new Player(Marker.O, null));
            this.iterations = iterations;
        }

        public void nextMove() {
        }

        public boolean isOver() {
            if (iterations == 0) {
                return true;
            }
            iterations--;
            return false;
        }
    }

    private class FakeUI extends UI {
        public String log = "";

        public FakeUI() {
            super(null, null);
        }

        @Override
        public void render() {
            log += "render ";
        }

        @Override
        public void message(String contents) {
            log += contents;
        }
    }
}
