package me.damonkelley.tictactoe;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardFormatterTest {
    @Test
    public void itCollectsAllCombinationsOfTheBoard() {
        Board board  = new Board();

        BoardFormatter formatter = new BoardFormatter(board);
        assertTrue(formatter.collect().contains(asList(null, null, null)));
        assertEquals(8, formatter.collect().size());
    }

    @Test
    public void itCollectsARowOfOMarkers() {
        Board board  = new Board();

        board.put(new Space(0, 0), Marker.O);
        board.put(new Space(1, 0), Marker.O);
        board.put(new Space(2, 0), Marker.O);

        BoardFormatter formatter = new BoardFormatter(board);
        assertTrue(formatter.collect().contains(asList(Marker.O, Marker.O, Marker.O)));
    }

    @Test
    public void itCollectsARowOfXMarkers() {
        Board board  = new Board();

        board.put(new Space(0, 0), Marker.X);
        board.put(new Space(1, 0), Marker.X);
        board.put(new Space(2, 0), Marker.X);

        BoardFormatter formatter = new BoardFormatter(board);
        assertTrue(formatter.collect().contains(asList(Marker.X, Marker.X, Marker.X)));
    }

    @Test
    public void itCollectsAColumnOfOMarkers() {
        Board board  = new Board();

        board.put(new Space(1, 0), Marker.O);
        board.put(new Space(1, 1), Marker.O);
        board.put(new Space(1, 2), Marker.O);

        BoardFormatter formatter = new BoardFormatter(board);
        assertTrue(formatter.collect().contains(asList(Marker.O, Marker.O, Marker.O)));
    }

    @Test
    public void itCollectsAColumnOfXMarkers() {
        Board board  = new Board();

        board.put(new Space(2, 0), Marker.X);
        board.put(new Space(2, 1), Marker.X);
        board.put(new Space(2, 2), Marker.X);

        BoardFormatter formatter = new BoardFormatter(board);
        assertTrue(formatter.collect().contains(asList(Marker.X, Marker.X, Marker.X)));
    }

    @Test
    public void itWillCollectADiagonalOfOMarkers() {
        Board board  = new Board();

        board.put(new Space(0, 0), Marker.O);
        board.put(new Space(1, 1), Marker.O);
        board.put(new Space(2, 2), Marker.O);

        BoardFormatter formatter = new BoardFormatter(board);
        assertTrue(formatter.collect().contains(asList(Marker.O, Marker.O, Marker.O)));
    }

    @Test
    public void itWillCollectADiagonalXfOMarkers() {
        Board board  = new Board();

        board.put(new Space(0, 2), Marker.X);
        board.put(new Space(1, 1), Marker.X);
        board.put(new Space(2, 0), Marker.X);

        BoardFormatter formatter = new BoardFormatter(board);
        assertTrue(formatter.collect().contains(asList(Marker.X, Marker.X, Marker.X)));
    }
}
