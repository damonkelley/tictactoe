package me.damonkelley.ui;

import me.damonkelley.tictactoe.Board;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Space;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BoardPresenterTest {

    @Test
    public void itPresentsAnEmptyBoard() {
        Board board = new Board();

        String expected = " 1 | 2 | 3 \n" +
                          "---+---+---\n" +
                          " 4 | 5 | 6 \n" +
                          "---+---+---\n" +
                          " 7 | 8 | 9 \n" +
                          "\n";

        assertEquals(expected, new BoardPresenter(board).present());
    }


    @Test
    public void itPresentsAGameWithMoves() {
        Board board = new Board()
                .put(new Space(0, 2), Marker.X);

        String expected = " 1 | 2 | 3 \n" +
                          "---+---+---\n" +
                          " 4 | 5 | 6 \n" +
                          "---+---+---\n" +
                          " X | 8 | 9 \n" +
                          "\n";

        assertEquals(expected, new BoardPresenter(board).present());
    }

    @Test
    public void itPresentsA4By4Game() {
        String expected = "  1  |  2  |  3  |  4  \n" +
                          "-----+-----+-----+-----\n" +
                          "  5  |  6  |  7  |  8  \n"  +
                          "-----+-----+-----+-----\n" +
                          "  9  |  10 |  11 |  12 \n"  +
                          "-----+-----+-----+-----\n" +
                          "  13 |  14 |  15 |  16 \n"  +
                          "\n";

        assertEquals(expected, new BoardPresenter(new Board(4)).present());
    }

    @Test
    public void itPresentsA4By4GameWithMoves() {
        Board board = new Board(4)
                .put(new Space(2, 3), Marker.X)
                .put(new Space(1, 1), Marker.O);

        String expected = "  1  |  2  |  3  |  4  \n" +
                          "-----+-----+-----+-----\n" +
                          "  5  |  O  |  7  |  8  \n"  +
                          "-----+-----+-----+-----\n" +
                          "  9  |  10 |  11 |  12 \n"  +
                          "-----+-----+-----+-----\n" +
                          "  13 |  14 |  X  |  16 \n"  +
                          "\n";

        assertEquals(expected, new BoardPresenter(board).present());
    }
}
