package me.damonkelley.ui;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Player;
import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe.finder.Finder;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class GamePresenterTest {

    @Test
    public void itPresentsAnEmptyGame() {
        Game game = new Game(Marker.X);

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
        Player player = new Player(Marker.X, new FakeFinder());

        Game game = new Game(Marker.X);

        player.move(game);

        String expected = " 1 | 2 | 3 \n" +
                          "---+---+---\n" +
                          " 4 | 5 | 6 \n" +
                          "---+---+---\n" +
                          " X | 8 | 9 \n" +
                          "\n";

        assertEquals(expected, new GamePresenter(game).present());
    }

    private class FakeFinder extends Finder {
        @Override
        public Space getNextMove(Game game) {
            return new Space(0, 2);
        }
    }
}
