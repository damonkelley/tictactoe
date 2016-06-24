import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void itCanQueryForTheContentsOfASpace() {
        Board board = new Board();
        assertEquals(null, board.get(new Space(0, 0)));
    }

    @Test
    public void itCanFillASpace() {
        Board board = new Board();

        board.put(new Space(0, 0), Marker.X);
        board.put(new Space(0, 1), Marker.O);

        assertEquals(Marker.X, board.get(new Space(0, 0)));
        assertEquals(Marker.O, board.get(new Space(0, 1)));
    }

    @Test
    public void itKnowsWhenItIsFull() {
        Board board = new Board();
        assertFalse(board.isFull());

        board.put(new Space(0,0), Marker.X);
        board.put(new Space(2, 0), Marker.O);
        board.put(new Space(0, 2), Marker.X);
        board.put(new Space(0, 1), Marker.O);
        board.put(new Space(1, 1), Marker.X);
        board.put(new Space(2, 2), Marker.O);
        board.put(new Space(1, 0), Marker.X);
        board.put(new Space(1, 2), Marker.O);
        board.put(new Space(2, 1), Marker.X);

        assertTrue(board.isFull());
    }

    @Test
    public void itCanTestEqualityWithOtherBoards() {
        Board boardWithOneSpaceFilled = new Board().put(new Space(1, 0), Marker.O);

        assertNotEquals(new Board(), boardWithOneSpaceFilled);
        assertEquals(new Board().put(new Space(1, 0), Marker.O), boardWithOneSpaceFilled);
    }

    @Test
    public void itCanMakeACopyOfItself() {
        Board board = new Board();
        assertEquals(new Board(), board);

        Board newBoard = board.copy()
                .put(new Space(0, 1), Marker.X);

        assertNotEquals(board, newBoard);
    }

    @Test
    public void itWillReportAllAvailableSpaces() {
        List<Space> availableSpaces = Arrays.asList(
                new Space(0, 0),
                new Space(1, 0),
                new Space(2, 0),
                new Space(1, 0),
                new Space(1, 1),
                new Space(1, 2),
                new Space(2, 0),
                new Space(2, 1),
                new Space(2, 2)
        );
        Board board =  new Board();

        assertTrue(board.availableSpaces().containsAll(availableSpaces));

        board.put(new Space(0, 0), Marker.X);
        assertFalse(board.availableSpaces().containsAll(availableSpaces));
    }
}
