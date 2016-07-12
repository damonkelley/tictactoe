package me.damonkelley.tictactoe;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {

    @Test
    public void theMarkerPassedIsTheFirstToMove() {
        assertEquals(Marker.O, new Game(Marker.O).nextTurn());
        assertEquals(Marker.X, new Game(Marker.X).nextTurn());
    }

    @Test
    public void player1Wins() {
        Game game = new Game(Marker.X);

        game.move(new Space(0, 0), Marker.X)
                .move(new Space(2, 0), Marker.O)
                .move(new Space(1, 1), Marker.X)
                .move(new Space(0, 2), Marker.O)
                .move(new Space(2, 2), Marker.X);

        assertEquals(true, game.isWinner(Marker.X));
        assertFalse(game.isDraw());
        assertTrue(game.isOver());
    }

    @Test
    public void itIsADraw() {
        Game game = new Game(Marker.O);

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
        Game game = new Game(Marker.O);
        assertEquals(Marker.O, game.nextTurn());
        assertEquals(Marker.O, game.nextTurn());

        game.move(new Space(0, 0), Marker.O);
        assertEquals(Marker.X, game.nextTurn());
    }

    @Test
    public void equalityIsDeterminedByState() {
        Game game1 = new Game(Marker.X);
        Game game2 = new Game(Marker.X);

        assertEquals(game1, game2);

        game2.move(new Space(0, 1), game1.nextTurn());

        assertNotEquals(game1, game2);
    }

    @Test
    public void itCanMove() {
        Game game = new Game(Marker.X);

        game.move(new Space(0, 0), Marker.X);

        assertEquals(game.getBoard().get(new Space(0, 0)), Marker.X);
    }

    @Test
    public void itOnlyAllowsMovesToAvailableSpaces() {
        Game game = new Game(Marker.X);

        assertEquals(game.getBoard().get(new Space(0, 0)), null);

        game.move(new Space(0, 0), Marker.X)
                .move(new Space(0, 0), Marker.O);

        assertEquals(game.getBoard().get(new Space(0, 0)), Marker.X);
    }

    @Test
    public void itCanResetItself() {
        Game game = new Game(Marker.X);
        game.move(new Space(0, 0), Marker.O);
        game.move(new Space(1, 1), Marker.O);
        game.move(new Space(2, 2), Marker.O);

        assertTrue(game.isOver());
        game.reset();
        assertFalse(game.isOver());
    }

    @Test
    public void itMaintainsTheSameBoardSizeAfterResetting() {
        Game game = new Game(Marker.X, 4);
        game.reset();
        assertEquals(4, game.getBoard().getSize());
    }

    @Test
    public void itCanCopyItself() {
        Game game = new Game(Marker.X);
        Game gameCopy = game.copy();

        assertEquals(game, gameCopy);

        game.move(new Space(0, 0), game.nextTurn());

        assertNotEquals(game, gameCopy);
    }

    @Test
    public void itIsNotOverWhenThereAreNoMarkersOnTheBoard() {
        assertEquals(false, new Game(Marker.O).isOver());
    }

    @Test
    public void itIsOverWhenThereIsAWinner() {
        Game game = new Game(Marker.O);

        game.move(new Space(0, 0), Marker.X)
                .move(new Space(1, 0), Marker.X)
                .move(new Space(2, 0), Marker.X);

        assertEquals(true, game.isOver());
    }

    @Test
    public void itCanDetermineWhenOIsTheWinner() {
        Game game = new Game(Marker.O);

        game.move(new Space(0, 0), Marker.X)
                .move(new Space(1, 0), Marker.O)
                .move(new Space(2, 0), Marker.X)
                .move(new Space(1, 1), Marker.O)
                .move(new Space(2, 2), Marker.X)
                .move(new Space(1, 2), Marker.O);

        assertEquals(true, game.isWinner(Marker.O));
    }

    @Test
    public void itCanDetermineWhenXIsTheWinner() {
        Game game = new Game(Marker.O);

        game.move(new Space(0, 0), Marker.X)
                .move(new Space(1, 0), Marker.O)
                .move(new Space(1, 1), Marker.X)
                .move(new Space(2, 0), Marker.O)
                .move(new Space(2, 2), Marker.X);

        assertEquals(true, game.isWinner(Marker.X));
    }

    @Test
    public void itDoesNotHaveAWinner() {
        Game game = new Game(Marker.O);

        game.move(new Space(0, 0), Marker.X)
                .move(new Space(1, 0), Marker.O);

        assertEquals(false, game.hasWinner());
    }

    @Test
    public void itIsNotADrawIfThereIsAWinner() {
        Game game = new Game(Marker.O);

        game.move(new Space(0, 0), Marker.X)
                .move(new Space(1, 0), Marker.O)
                .move(new Space(1, 1), Marker.X)
                .move(new Space(2, 0), Marker.O)
                .move(new Space(2, 2), Marker.X);

        assertEquals(false, game.isDraw());
    }

    @Test
    public void itIsNotADrawIfTheBoardIsFullAndThereIsAWinner() {
        Game game = new Game(Marker.O);

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
        Game game = new Game(Marker.O);

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
}
