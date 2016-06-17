import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;


public class StateTest {
    private State state;

    @Before
    public void setUp() throws Exception {
        this.state = new State();
    }

    @Test
    public void xWinsTheGame() {
        state.move(new Point(0, 0), Player.X);
        state.move(new Point(1, 0), Player.O);
        state.move(new Point(1, 1), Player.X);
        state.move(new Point(2, 0), Player.O);
        state.move(new Point(2, 2), Player.X);

        assertEquals(state.getWinner(), Player.X);
    }

    @Test
    public void oWinsTheGame() {
        state.move(new Point(0, 0), Player.X);
        state.move(new Point(1, 0), Player.O);
        state.move(new Point(2, 0), Player.X);
        state.move(new Point(1, 1), Player.O);
        state.move(new Point(2, 2), Player.X);
        state.move(new Point(2, 1), Player.O);

        assertEquals(state.getWinner(), Player.O);
    }

    @Test
    public void moveAddsMarker() {
        state.move(new Point(1, 0), Player.X);
        assertEquals(Player.X, state.getBoard().get(new Point(0,1)));
    }

    @Test
    public void gameIsOver() {
        assertEquals(false, state.isOver());

        state.move(new Point(0,0), Player.X);
        state.move(new Point(1,0), Player.X);
        state.move(new Point(2,0), Player.X);

        assertEquals(Player.X, state.getWinner());
        assertEquals(true, state.isOver());
    }

    @Test
    public void gameIsOverWithADraw() {
        makeDraw();
        assertEquals(true, state.isDraw());
        assertEquals(true, state.isOver());
    }

    @Test
    public void gameIsDraw() {
        assertEquals(false, state.isDraw());
        makeDraw();
        assertEquals(true, state.isDraw());
    }

    private void makeDraw() {
        state.move(new Point(0,0), Player.X);
        state.move(new Point(2, 0), Player.O);
        state.move(new Point(0, 2), Player.X);
        state.move(new Point(0, 1), Player.O);
        state.move(new Point(1, 1), Player.X);
        state.move(new Point(2, 2), Player.O);
        state.move(new Point(1, 0), Player.X);
        state.move(new Point(1, 2), Player.O);
        state.move(new Point(2, 1), Player.X);
    }

}