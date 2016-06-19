import org.junit.Test;

import java.awt.*;

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

        board.put(new Point(0, 0), Player.X);
        board.put(new Point(0, 1), Player.O);

        assertEquals(Player.X, board.get(new Point(0, 0)));
        assertEquals(Player.O, board.get(new Point(0, 1)));
    }

    @Test
    public void itKnowsWhenItIsFull() {
        Board board = new Board();
        assertFalse(board.isFull());

        board.put(new Point(0,0), Player.X);
        board.put(new Point(2, 0), Player.O);
        board.put(new Point(0, 2), Player.X);
        board.put(new Point(0, 1), Player.O);
        board.put(new Point(1, 1), Player.X);
        board.put(new Point(2, 2), Player.O);
        board.put(new Point(1, 0), Player.X);
        board.put(new Point(1, 2), Player.O);
        board.put(new Point(2, 1), Player.X);

        assertTrue(board.isFull());
    }

}
