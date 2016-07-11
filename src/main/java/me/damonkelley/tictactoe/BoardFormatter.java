package me.damonkelley.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
        collection.addAll(getClusters());

        return collection;
    }

    private List<List<Marker>> getClusters() {
        List<List<Marker>> clusters = new ArrayList<>();
        if (board.getSize() == 3) return clusters;

        getClusterParents().forEach(space -> clusters.add(collectCluster(space)));
        return clusters;
    }

    private List<Marker> collectCluster(Space space) {
        int x = space.x;
        int y = space.y;
        return asList(
                board.get(space),
                board.get(new Space(x + 1, y)),
                board.get(new Space(x, y + 1)),
                board.get(new Space(x + 1, y + 1)));
    }

    private List<Space> getClusterParents() {
        return board.getSpaces().entrySet()
                .stream()
                .filter(e -> e.getKey().y < maxXOrYValue() && e.getKey().x < maxXOrYValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private List<List<Marker>> getRows() {
        return groupSpacesBy(this::getY);
    }

    private List<List<Marker>> getColumns() {
        return groupSpacesBy(this::getX);
    }

    private List<List<Marker>> groupSpacesBy(Function<Map.Entry<Space, Marker>, Integer> getY) {
        return board.getSpaces().entrySet()
                .stream()
                .collect(Collectors.groupingBy(getY))
                .values()
                .stream()
                .map(e -> e.stream().map(Map.Entry::getValue).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private int getY(Map.Entry<Space, Marker> e) {
        return e.getKey().y;
    }

    private int getX(Map.Entry<Space, Marker> e) {
        return e.getKey().x;
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
        int x = maxXOrYValue();
        int y = minXorYValue();
        List<Marker> markers = new ArrayList<>();

        for (Space space : board) {
            if (space.getY() == y && space.getX() == x) {
                markers.add(board.get(space));
                x--;
                y++;
            }
        }
        return markers;
    }

    private int minXorYValue() {
        return 0;
    }

    private int maxXOrYValue() {
        return board.getSize() - 1;
    }
}
