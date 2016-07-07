package me.damonkelley.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class BoardFormatter {
    private Board board;

    public BoardFormatter(Board board) {
        this.board = board;
    }

    public List<List<Marker>> collect() {
        List<List<Marker>> collection = new ArrayList<>();

        collection.addAll(getRows());
        collection.addAll(getColumns());
        collection.addAll(getDiagonals());

        return collection;
    }

    private List<List<Marker>> getRows() {
        return board.getSpaces().entrySet()
                .stream()
                .collect(Collectors.groupingBy(e -> e.getKey().getY()))
                .values()
                .stream()
                .map(e -> e.stream().map(row -> row.getValue()).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private List<List<Marker>> getColumns() {
        return board.getSpaces().entrySet()
                .stream()
                .collect(Collectors.groupingBy(e -> e.getKey().getX()))
                .values()
                .stream()
                .map(e -> e.stream().map(row -> row.getValue()).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private List<List<Marker>> getDiagonals() {
        return asList(getLeftDiagonal(), getRightDiagonal());
    }

    private List<Marker> getLeftDiagonal() {
        List<Marker> markers = new ArrayList<>();
        for (Space space : board) {
            if (space.getX() == space.getY()) {
                markers.add(board.get(space));
            }
        }
        return markers;
    }

    private List<Marker> getRightDiagonal() {
        int x = 2;
        int y = 0;
        List<Marker> markers = new ArrayList<>();

        for (Space space : board) {
            if (space.getY() == y && space.getX() == x) {
                markers.add(board.get(space));
                x--; y++;
            }
        }
        return markers;
    }
}
