import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class GameTest {
    private Game game;

    @Before
    public void setUp() throws Exception {
        this.game = new Game("", new StringBuffer());
    }

    @Test
    public void xWinsTheGame() {
        game.move(0, Player.X);
        game.move(1, Player.O);
        game.move(4, Player.X);
        game.move(2, Player.O);
        game.move(8, Player.X);

        assertEquals(game.getWinner(), Player.X);
    }

    @Test
    public void oWinsTheGame() {
        game.move(0, Player.X);
        game.move(1, Player.O);
        game.move(2, Player.X);
        game.move(4, Player.O);
        game.move(8, Player.X);
        game.move(7, Player.O);

        assertEquals(game.getWinner(), Player.O);
    }

    @Test
    public void moveAddsMarker() {
        game.move(1, Player.X);
        assertEquals(Player.X, game.getBoard().get(1));
    }

    @Test
    public void gameIsOver() {
        assertEquals(false, game.isOver());

        game.move(0, Player.X);
        game.move(1, Player.X);
        game.move(2, Player.X);

        assertEquals(Player.X, game.getWinner());
        assertEquals(true, game.isOver());
    }

    @Test
    public void gameIsOverWithADraw() {
        makeDraw();
        assertEquals(true, game.isDraw());
        assertEquals(true, game.isOver());
    }

    @Test
    public void gameIsDraw() {
        assertEquals(false, game.isDraw());
        makeDraw();
        assertEquals(true, game.isDraw());
    }

    private void makeDraw() {
        game.move(0, Player.X);
        game.move(2, Player.O);
        game.move(6, Player.X);
        game.move(3, Player.O);
        game.move(4, Player.X);
        game.move(8, Player.O);
        game.move(1, Player.X);
        game.move(7, Player.O);
        game.move(5, Player.X);
    }

}