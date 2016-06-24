import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class GamePresenterTest {
    @Test
    public void itPresentsAnEmptyGame() {
        Game game = new Game(new FakePlayer(Marker.X), new FakePlayer(Marker.O));
        String expected = " 1 | 2 | 3 \n" +
                          "---+---+---\n" +
                          " 4 | 5 | 6 \n" +
                          "---+---+---\n" +
                          " 7 | 8 | 9 \n" +
                          "\n";

        assertEquals(expected, new GamePresenter(game).present());
    }

    @Test
    public void itPresentsAGameWithMoves() {
        Game game = new Game(new FakePlayer(Marker.X), new FakePlayer(Marker.O));

        game.nextMove();

        String expected = " 1 | 2 | 3 \n" +
                          "---+---+---\n" +
                          " 4 | 5 | 6 \n" +
                          "---+---+---\n" +
                          " X | 8 | 9 \n" +
                          "\n";

        assertEquals(expected, new GamePresenter(game).present());
    }

    private class FakePlayer extends Player {
        public FakePlayer(Marker marker) {
            super(marker);
        }

        @Override
        public void move(State state) {
            state.move(new Space(0, 2), getMarker());
        }
    }
}
