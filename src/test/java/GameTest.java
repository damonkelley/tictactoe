import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void itAlternatesPlayerMoves() {

        QueueBackedFinder player1Finder = new QueueBackedFinder();
        QueueBackedFinder player2Finder = new QueueBackedFinder();

        Player player1 = new Player(Marker.X, player1Finder);
        Player player2 = new Player(Marker.O, player2Finder);

        player1Finder.queueMove(new Space(0, 0));
        player2Finder.queueMove(new Space(0, 1));

        Game game = new Game(player1, player2);

        game.nextMove();
        game.nextMove();

        assertEquals(player1.getMarker(), game.getBoard().get(new Space(0, 0)));
        assertEquals(player2.getMarker(), game.getBoard().get(new Space(0, 1)));
    }

    @Test
    public void theFirstPlayerPassedIsTheFirstToMove() {
        QueueBackedFinder player1Finder = new QueueBackedFinder();
        QueueBackedFinder player2Finder = new QueueBackedFinder();

        Player player1 = new Player(Marker.O, player1Finder);
        Player player2 = new Player(Marker.X, player2Finder);

        player1Finder.queueMove(new Space(0, 0));

        Game game = new Game(player1, player2);
        game.nextMove();

        assertEquals(Marker.O, game.getBoard().get(new Space(0, 0)));
    }

    @Test
    public void player1Wins() {
        QueueBackedFinder player1Finder = new QueueBackedFinder();
        QueueBackedFinder player2Finder = new QueueBackedFinder();

        Player player1 = new Player(Marker.X, player1Finder);
        Player player2 = new Player(Marker.O, player2Finder);

        player1Finder.queueMove(new Space(0, 0));
        player2Finder.queueMove(new Space(2, 0));
        player1Finder.queueMove(new Space(1, 1));
        player2Finder.queueMove(new Space(0, 2));
        player1Finder.queueMove(new Space(2, 2));

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
        QueueBackedFinder player1Finder = new QueueBackedFinder();
        QueueBackedFinder player2Finder = new QueueBackedFinder();

        Player player1 = new Player(Marker.O, player1Finder);
        Player player2 = new Player(Marker.X, player2Finder);

        player1Finder.queueMove(new Space(0, 0));
        player2Finder.queueMove(new Space(1, 1));
        player1Finder.queueMove(new Space(2, 0));
        player2Finder.queueMove(new Space(1, 0));
        player1Finder.queueMove(new Space(2, 1));
        player2Finder.queueMove(new Space(0, 1));
        player1Finder.queueMove(new Space(1, 2));
        player2Finder.queueMove(new Space(2, 2));
        player1Finder.queueMove(new Space(0, 2));

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

    private class QueueBackedFinder implements Finder {
        private Queue<Space> moveQueue = new ArrayDeque<>();

        public void queueMove(Space space) {
            moveQueue.add(space);
        }

        @Override
        public Space getNextMove(State state) {
            return moveQueue.remove();
        }
    }
}
