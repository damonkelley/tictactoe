import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class StateTest {
    private State state;

    @Before
    public void setUp() throws Exception {
        this.state = new State();
    }

    @Test
    public void xWinsTheGame() {
        state.move(0, Player.X);
        state.move(1, Player.O);
        state.move(4, Player.X);
        state.move(2, Player.O);
        state.move(8, Player.X);

        assertEquals(state.getWinner(), Player.X);
    }

    @Test
    public void oWinsTheGame() {
        state.move(0, Player.X);
        state.move(1, Player.O);
        state.move(2, Player.X);
        state.move(4, Player.O);
        state.move(8, Player.X);
        state.move(7, Player.O);

        assertEquals(state.getWinner(), Player.O);
    }

    @Test
    public void moveAddsMarker() {
        state.move(1, Player.X);
        assertEquals(Player.X, state.getBoard().get(1));
    }

    @Test
    public void gameIsOver() {
        assertEquals(false, state.isOver());

        state.move(0, Player.X);
        state.move(1, Player.X);
        state.move(2, Player.X);

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
        state.move(0, Player.X);
        state.move(2, Player.O);
        state.move(6, Player.X);
        state.move(3, Player.O);
        state.move(4, Player.X);
        state.move(8, Player.O);
        state.move(1, Player.X);
        state.move(7, Player.O);
        state.move(5, Player.X);
    }

}