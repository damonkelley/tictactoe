import org.junit.Test;
import sun.jvm.hotspot.oops.Mark;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest {

    @Test
    public void itAlternatesPlayerMoves() {
        HumanPlayer player1 = new HumanPlayer(Marker.X);
        HumanPlayer player2 = new HumanPlayer(Marker.O);
        State state = new State();

        player1.queueMove(new Point(0, 0));
        player2.queueMove(new Point(0, 1));

        Game game = new Game(state, player1, player2);

        game.nextMove();
        game.nextMove();

        assertEquals(player1.getMarker(), state.getBoard().get(new Point(0, 0)));
        assertEquals(player2.getMarker(), state.getBoard().get(new Point(0, 1)));
    }

    @Test
    public void player1Wins() {
        HumanPlayer player1 = new HumanPlayer(Marker.X);
        HumanPlayer player2 = new HumanPlayer(Marker.O);

        player1.queueMove(new Point(0, 0));
        player2.queueMove(new Point(2, 0));
        player1.queueMove(new Point(1, 1));
        player2.queueMove(new Point(0, 2));
        player1.queueMove(new Point(2, 2));

        Game game = new Game(player1, player2);

        game.nextMove();
        game.nextMove();
        game.nextMove();
        game.nextMove();
        game.nextMove();

        assertEquals(player1, game.getWinner());
        assertFalse(game.isDraw());
        assertTrue(game.isOver());
    }

    @Test
    public void itIsADraw() {
        HumanPlayer player1 = new HumanPlayer(Marker.O);
        HumanPlayer player2 = new HumanPlayer(Marker.X);

        player1.queueMove(new Point(0, 0));
        player2.queueMove(new Point(1, 1));
        player1.queueMove(new Point(2, 0));
        player2.queueMove(new Point(1, 0));
        player1.queueMove(new Point(2, 1));
        player2.queueMove(new Point(0, 1));
        player1.queueMove(new Point(1, 2));
        player2.queueMove(new Point(2, 2));
        player1.queueMove(new Point(0, 2));

        Game game = new Game(player1, player2);

        game.nextMove();
        game.nextMove();
        game.nextMove();
        game.nextMove();
        game.nextMove();
        game.nextMove();
        game.nextMove();
        game.nextMove();
        game.nextMove();

        assertTrue(game.isDraw());
        assertTrue(game.isOver());
    }
}
