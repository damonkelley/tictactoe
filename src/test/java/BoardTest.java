import org.junit.Test;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void itCanQueryForTheContentsOfASpace() {
        Board board = new Board();
        assertEquals(null, board.get(new Point(0, 0)));
    }

    @Test
    public void itCanFillASpace() {
        Board board = new Board();

        board.put(new Point(0, 0), PlayerMarker.X);
        board.put(new Point(0, 1), PlayerMarker.O);

        assertEquals(PlayerMarker.X, board.get(new Point(0, 0)));
        assertEquals(PlayerMarker.O, board.get(new Point(0, 1)));
    }

    @Test
    public void itKnowsWhenItIsFull() {
        Board board = new Board();
        assertFalse(board.isFull());

        board.put(new Point(0,0), PlayerMarker.X);
        board.put(new Point(2, 0), PlayerMarker.O);
        board.put(new Point(0, 2), PlayerMarker.X);
        board.put(new Point(0, 1), PlayerMarker.O);
        board.put(new Point(1, 1), PlayerMarker.X);
        board.put(new Point(2, 2), PlayerMarker.O);
        board.put(new Point(1, 0), PlayerMarker.X);
        board.put(new Point(1, 2), PlayerMarker.O);
        board.put(new Point(2, 1), PlayerMarker.X);

        assertTrue(board.isFull());
    }

    @Test
    public void itCanTestEqualityWithOtherBoards() {
        Board boardWithOneSpaceFilled = new Board().put(new Point(1, 0), PlayerMarker.O);

        assertNotEquals(new Board(), boardWithOneSpaceFilled);
        assertEquals(new Board().put(new Point(1, 0), PlayerMarker.O), boardWithOneSpaceFilled);
    }

    @Test
    public void itCanMakeACopyOfItself() {
        Board board = new Board();
        assertEquals(new Board(), board);

        Board newBoard = board.copy()
                .put(new Point(0, 1), PlayerMarker.X);

        assertNotEquals(board, newBoard);
    }

    @Test
    public void itWillReportAllAvailableSpaces() {
        List<Point> availableSpaces = Arrays.asList(
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(1, 0),
                new Point(1, 1),
                new Point(1, 2),
                new Point(2, 0),
                new Point(2, 1),
                new Point(2, 2)
        );

        Board board =  new Board();

        assertTrue(board.availableSpaces().containsAll(availableSpaces));

        board.put(new Point(0, 0), PlayerMarker.X);
        assertFalse(board.availableSpaces().containsAll(availableSpaces));
    }
}
