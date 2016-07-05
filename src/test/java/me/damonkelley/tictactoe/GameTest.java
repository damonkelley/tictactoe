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
        Player player1 = new Player(Marker.X, new NullFinder());
        Player player2 = new Player(Marker.O, new NullFinder());
        Game game = new Game(player1, player2);

        game.move(new Space(0, 0), Marker.X)
                .move(new Space(2, 0), Marker.O)
                .move(new Space(1, 1), Marker.X)
                .move(new Space(0, 2), Marker.O)
                .move(new Space(2, 2), Marker.X);

        assertEquals(player1.getMarker(), game.determineWinner());
        assertFalse(game.isDraw());
        assertTrue(game.isOver());
    }

    @Test
    public void itIsADraw() {
        Game game = new Game(new Player(Marker.O, new NullFinder()), new Player(Marker.X, new NullFinder()));

        game.move(new Space(0, 0), Marker.O)
                .move(new Space(1, 1), Marker.X)
                .move(new Space(2, 0), Marker.O)
                .move(new Space(1, 0), Marker.X)
                .move(new Space(2, 1), Marker.O)
                .move(new Space(0, 1), Marker.X)
                .move(new Space(1, 2), Marker.O)
                .move(new Space(2, 2), Marker.X)
                .move(new Space(0, 2), Marker.O);

        assertTrue(game.isDraw());
        assertTrue(game.isOver());
    }

    @Test
    public void itKnowsWhichMarkerIsNext() {
        Game game = new Game(new Player(Marker.O, new NullFinder()), new Player(Marker.X, new NullFinder()));
        assertEquals(Marker.O, game.nextTurn());
        assertEquals(Marker.O, game.nextTurn());

        game.move(new Space(0, 0), Marker.O);
        assertEquals(Marker.X, game.nextTurn());
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
    public void itCanMove() {
        Game game = new Game(PlayerFactory.computer(Marker.X), PlayerFactory.computer(Marker.O));

        game.move(new Space(0, 0), Marker.X);
        assertEquals(game.getBoard().get(new Space(0, 0)), Marker.X);
    }

    @Test
    public void itOnlyAllowsMovesToAvailableSpaces() {
        Game game = new Game(PlayerFactory.computer(Marker.X), PlayerFactory.computer(Marker.O));

        assertEquals(game.getBoard().get(new Space(0, 0)), null);

        game.move(new Space(0, 0), Marker.X)
                .move(new Space(0, 0), Marker.O);

        assertEquals(game.getBoard().get(new Space(0, 0)), Marker.X);
    }

    @Test
    public void itCanResetItself() {
        Game game = new Game(PlayerFactory.computer(Marker.X), PlayerFactory.computer(Marker.O));

        game.nextMove()
                .nextMove()
                .nextMove()
                .nextMove()
                .nextMove()
                .nextMove()
                .nextMove()
                .nextMove()
                .nextMove();

        assertTrue(game.isOver());

        game.reset();

        assertFalse(game.isOver());
    }

    @Test
    public void itCanCopyItself() {
        Game game = new Game(PlayerFactory.computer(Marker.X), PlayerFactory.computer(Marker.O));
        Game gameCopy = game.copy();

        assertEquals(game, gameCopy);

        gameCopy.nextMove();

        assertNotEquals(game, gameCopy);
    }

    @Test
    public void itIsNotOverWhenThereAreNoMarkersOnTheBoard() {
        Game game = new Game(new Player(Marker.O, new NullFinder()), new Player(Marker.X, new NullFinder()));

        assertEquals(false, game.isOver());
    }

    @Test
    public void itIsOverWhenThereIsAWinner() {
        Game game = new Game(new Player(Marker.O, new NullFinder()), new Player(Marker.X, new NullFinder()));

        game.move(new Space(0, 0), Marker.X)
                .move(new Space(1, 0), Marker.X)
                .move(new Space(2, 0), Marker.X);

        assertEquals(true, game.isOver());
    }

    @Test
    public void itCanDetermineWhenOIsTheWinner() {
        Game game = new Game(new Player(Marker.O, new NullFinder()), new Player(Marker.X, new NullFinder()));

        game.move(new Space(0, 0), Marker.X)
                .move(new Space(1, 0), Marker.O)
                .move(new Space(2, 0), Marker.X)
                .move(new Space(1, 1), Marker.O)
                .move(new Space(2, 2), Marker.X)
                .move(new Space(1, 2), Marker.O);

        assertEquals(Marker.O, game.determineWinner());
    }

    @Test
    public void itCanDetermineWhenXIsTheWinner() {
        Game game = new Game(new Player(Marker.O, new NullFinder()), new Player(Marker.X, new NullFinder()));

        game.move(new Space(0, 0), Marker.X)
                .move(new Space(1, 0), Marker.O)
                .move(new Space(1, 1), Marker.X)
                .move(new Space(2, 0), Marker.O)
                .move(new Space(2, 2), Marker.X);

        assertEquals(Marker.X, game.determineWinner());
    }

    @Test
    public void itCanDetermineWhenThereIsNoWinner() {
        Game game = new Game(new Player(Marker.O, new NullFinder()), new Player(Marker.X, new NullFinder()));

        game.move(new Space(0, 0), Marker.X)
                .move(new Space(1, 0), Marker.O);

        assertEquals(null, game.determineWinner());
    }

    @Test
    public void itIsNotADrawIfThereIsAWinner() {
        Game game = new Game(new Player(Marker.O, new NullFinder()), new Player(Marker.X, new NullFinder()));

        game.move(new Space(0, 0), Marker.X)
                .move(new Space(1, 0), Marker.O)
                .move(new Space(1, 1), Marker.X)
                .move(new Space(2, 0), Marker.O)
                .move(new Space(2, 2), Marker.X);

        assertEquals(false, game.isDraw());
    }

    @Test
    public void itIsNotADrawIfTheBoardIsFullAndThereIsAWinner() {
        Game game = new Game(new Player(Marker.O, new NullFinder()), new Player(Marker.X, new NullFinder()));

        game.move(new Space(0, 0), Marker.X)
                .move(new Space(2, 0), Marker.O)
                .move(new Space(0, 2), Marker.X)
                .move(new Space(0, 1), Marker.O)
                .move(new Space(1, 1), Marker.X)
                .move(new Space(2, 2), Marker.X)
                .move(new Space(1, 0), Marker.X)
                .move(new Space(1, 2), Marker.O)
                .move(new Space(2, 1), Marker.X);

        assertEquals(false, game.isDraw());
    }

    @Test
    public void itIsADrawIfThereIsNoWinnerAndNoMoreSpaces() {
        Game game = new Game(new Player(Marker.O, new NullFinder()), new Player(Marker.X, new NullFinder()));

        game.move(new Space(0, 0), Marker.X)
                .move(new Space(2, 0), Marker.O)
                .move(new Space(0, 2), Marker.X)
                .move(new Space(0, 1), Marker.O)
                .move(new Space(1, 1), Marker.X)
                .move(new Space(2, 2), Marker.O)
                .move(new Space(1, 0), Marker.X)
                .move(new Space(1, 2), Marker.O)
                .move(new Space(2, 1), Marker.X);

        assertEquals(true, game.isDraw());
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

    private class NullFinder extends Finder {

        @Override
        public Space getNextMove(Game game) {
            return null;
        }
    }
}
