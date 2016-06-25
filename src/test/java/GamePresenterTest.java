import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class GamePresenterTest {

    @Test
    public void itPresentsAnEmptyGame() {
        Player player1 = new Player(Marker.X, new FakeFinder());
        Player player2 = new Player(Marker.O, new FakeFinder());
        Game game = new Game(player1, player2);

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
        Player player1 = new Player(Marker.X, new FakeFinder());
        Player player2 = new Player(Marker.O, new FakeFinder());
        Game game = new Game(player1, player2);

        game.nextMove();

        String expected = " 1 | 2 | 3 \n" +
                          "---+---+---\n" +
                          " 4 | 5 | 6 \n" +
                          "---+---+---\n" +
                          " X | 8 | 9 \n" +
                          "\n";

        assertEquals(expected, new GamePresenter(game).present());
    }

    private class FakeFinder implements Finder {
        @Override
        public Space getNextMove(Game game) {
            return new Space(0, 2);
        }
    }
}
