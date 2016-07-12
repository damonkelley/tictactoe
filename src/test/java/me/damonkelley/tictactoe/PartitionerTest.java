package me.damonkelley.tictactoe;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PartitionerTest {
    @Test
    public void itPartitionsAllCombinationsOfTheBoard() {
        Board board  = new Board();

        Partitioner partitioner = new Partitioner(board);
        assertEquals(8, partitioner.partition().size());
    }

    @Test
    public void itPartitionsARowOfOMarkers() {
        Board board  = new Board();

        board.put(new Space(0, 0), Marker.O);
        board.put(new Space(1, 0), Marker.O);
        board.put(new Space(2, 0), Marker.O);

        Partitioner partitioner = new Partitioner(board);
        assertTrue(partitioner.partition().contains(asList(Marker.O, Marker.O, Marker.O)));
    }

    @Test
    public void itPartitionsARowOfXMarkers() {
        Board board  = new Board();

        board.put(new Space(0, 0), Marker.X);
        board.put(new Space(1, 0), Marker.X);
        board.put(new Space(2, 0), Marker.X);

        Partitioner partitioner = new Partitioner(board);
        assertTrue(partitioner.partition().contains(asList(Marker.X, Marker.X, Marker.X)));
    }

    @Test
    public void itPartitionsAColumnOfOMarkers() {
        Board board  = new Board();

        board.put(new Space(1, 0), Marker.O);
        board.put(new Space(1, 1), Marker.O);
        board.put(new Space(1, 2), Marker.O);

        Partitioner partitioner = new Partitioner(board);
        assertTrue(partitioner.partition().contains(asList(Marker.O, Marker.O, Marker.O)));
    }

    @Test
    public void itPartitionsAColumnOfXMarkers() {
        Board board  = new Board();

        board.put(new Space(2, 0), Marker.X);
        board.put(new Space(2, 1), Marker.X);
        board.put(new Space(2, 2), Marker.X);

        Partitioner partitioner = new Partitioner(board);
        assertTrue(partitioner.partition().contains(asList(Marker.X, Marker.X, Marker.X)));
    }

    @Test
    public void itWillPartitionADiagonalOfOMarkers() {
        Board board  = new Board();

        board.put(new Space(0, 0), Marker.O);
        board.put(new Space(1, 1), Marker.O);
        board.put(new Space(2, 2), Marker.O);

        Partitioner partitioner = new Partitioner(board);
        assertTrue(partitioner.partition().contains(asList(Marker.O, Marker.O, Marker.O)));
    }

    @Test
    public void itWillPartitionADiagonalXfOMarkers() {
        Board board  = new Board();

        board.put(new Space(0, 2), Marker.X);
        board.put(new Space(1, 1), Marker.X);
        board.put(new Space(2, 0), Marker.X);

        Partitioner partitioner = new Partitioner(board);
        assertTrue(partitioner.partition().contains(asList(Marker.X, Marker.X, Marker.X)));
    }

    @Test
    public void itWillPartitionTheClusters() {
        Board board  = new Board(4);

        Partitioner partitioner = new Partitioner(board);
        assertEquals(19, partitioner.partition().size());
    }

    @Test
    public void itWillPartitionAClusterOfXMarkers() {
        Board board  = new Board(4);

        board.put(new Space(0, 0), Marker.X);
        board.put(new Space(1, 0), Marker.X);
        board.put(new Space(0, 1), Marker.X);
        board.put(new Space(1, 1), Marker.X);

        Partitioner partitioner = new Partitioner(board);
        assertTrue(partitioner.partition().contains(asList(Marker.X, Marker.X, Marker.X, Marker.X)));
    }

    @Test
    public void itWillPartitionAClusterOfOMarkers() {
        Board board  = new Board(4);

        board.put(new Space(0, 1), Marker.O);
        board.put(new Space(1, 1), Marker.O);
        board.put(new Space(0, 2), Marker.O);
        board.put(new Space(1, 2), Marker.O);

        Partitioner partitioner = new Partitioner(board);
        assertTrue(partitioner.partition().contains(asList(Marker.O, Marker.O, Marker.O, Marker.O)));
    }

    @Test
    public void allPartitionsHaveTheSameLengthAsTheBoardSize() {
        Partitioner partitioner = new Partitioner(new Board());
        partitioner.partition().forEach(partition -> assertEquals(3, partition.size()));

        partitioner = new Partitioner(new Board(4));
        partitioner.partition().forEach(partition -> assertEquals(4, partition.size()));
    }
}
