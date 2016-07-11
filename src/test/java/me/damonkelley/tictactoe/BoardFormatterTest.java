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

    @Test
    public void itWillCollectTheClusters() {
        Board board  = new Board(4);

        BoardFormatter formatter = new BoardFormatter(board);
        assertEquals(19, formatter.collect().size());
    }

    @Test
    public void itWillCollectAClusterOfXMarkers() {
        Board board  = new Board(4);

        board.put(new Space(0, 0), Marker.X);
        board.put(new Space(1, 0), Marker.X);
        board.put(new Space(0, 1), Marker.X);
        board.put(new Space(1, 1), Marker.X);

        BoardFormatter formatter = new BoardFormatter(board);
        assertTrue(formatter.collect().contains(asList(Marker.X, Marker.X, Marker.X, Marker.X)));
    }

    @Test
    public void itWillCollectAClusterOfOMarkers() {
        Board board  = new Board(4);

        board.put(new Space(0, 1), Marker.O);
        board.put(new Space(1, 1), Marker.O);
        board.put(new Space(0, 2), Marker.O);
        board.put(new Space(1, 2), Marker.O);

        BoardFormatter formatter = new BoardFormatter(board);
        assertTrue(formatter.collect().contains(asList(Marker.O, Marker.O, Marker.O, Marker.O)));
    }

    @Test
    public void allCollectionsHaveTheSameLengthAsTheBoardSize() {
        BoardFormatter formatter = new BoardFormatter(new Board());
        formatter.collect().forEach(collection -> assertEquals(3, collection.size()));

        formatter = new BoardFormatter(new Board(4));
        formatter.collect().forEach(collection -> assertEquals(4, collection.size()));
    }
}
