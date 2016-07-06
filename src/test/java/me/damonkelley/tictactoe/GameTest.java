package me.damonkelley.tictactoe;

import me.damonkelley.tictactoe.finder.Finder;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void equalityIsDeterminedByState() {
        Game game1 = new Game(PlayerFactory.computer(Marker.X), PlayerFactory.computer(Marker.O));
        Game game2 = new Game(PlayerFactory.computer(Marker.X), PlayerFactory.computer(Marker.O));

        assertEquals(game1, game2);

        game2.nextMove();

        assertNotEquals(game1, game2);
    }

    @Test
    public void equalityIsDeterminedByPlayers() {
        Game game1 = new Game(PlayerFactory.computer(Marker.X), PlayerFactory.computer(Marker.O));
        Game game2 = new Game(PlayerFactory.computer(Marker.X), PlayerFactory.computer(Marker.O));

        Game game3 = new Game(PlayerFactory.computer(Marker.O), PlayerFactory.computer(Marker.X));

        assertEquals(game1, game2);
        assertNotEquals(game1, game3);
    }

    @Test
    public void itCanResetItself() {
        Game game = new Game(PlayerFactory.computer(Marker.X), PlayerFactory.computer(Marker.O));

        game.nextMove();
        game.nextMove();
        game.nextMove();
        game.nextMove();
        game.nextMove();
        game.nextMove();
        game.nextMove();
        game.nextMove();
        game.nextMove();

        assertTrue(game.isOver());
        game.reset();
        assertFalse(game.isOver());
    }

    private class QueueBackedFinder extends Finder {
        private Queue<Space> moveQueue = new ArrayDeque<>();

        public void queueMove(Space space) {
            moveQueue.add(space);
        }

        @Override
        public Space getNextMove(Game game) {
            return moveQueue.remove();
        }
    }
}
