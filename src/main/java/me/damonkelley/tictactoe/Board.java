package me.damonkelley.tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board implements Iterable<Space> {

    private final int size;
    private Map<Space, Marker> spaces;

    public Board(int size) {
        this.size = size;
        this.spaces = new HashMap<>();
        build(size);
    }

    public Board() {
        this(3);
    }

    private Board(Map<Space, Marker> spaces) {
        this.size = 3;
        this.spaces = spaces;
    }

    public int getSize() {
        return size;
    }

    private void build(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.spaces.put(new Space(i, j), null);
            }
        }
    }

    public Marker get(Space space) {
        return spaces.get(space);
    }

    public Board put(Space space, Marker marker) {
        spaces.put(space, marker);
        return this;
    }

    public boolean isFull() {
        return spaces.entrySet()
                .stream()
                .noneMatch(m -> m.getValue() == null);
    }

    Map<Space, Marker> getSpaces() {
        return spaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        return spaces != null ? spaces.equals(board.spaces) : board.spaces == null;
    }

    @Override
    public int hashCode() {
        return spaces != null ? spaces.hashCode() : 0;
    }

    public Board copy() {
        return new Board(new HashMap<>(this.spaces));
    }

    public List<Space> availableSpaces() {
        return this.spaces.entrySet()
                .stream()
                .filter(space -> space.getValue() == null)
                .map(space -> space.getKey())
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Space> iterator() {
        List<Space> keys = new ArrayList<>(spaces.keySet());

        keys.sort((space, o) -> space.compareTo(o));
        return keys.iterator();
    }
}