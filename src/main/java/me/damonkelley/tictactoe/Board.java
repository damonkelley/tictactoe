package me.damonkelley.tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board implements Iterable<Space> {
    private Map<Space, Marker> spaces;

    public Board() {
        this.spaces = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.spaces.put(new Space(i, j), null);
            }
        }
    }

    private Board(Map<Space, Marker> spaces) {
        this.spaces = spaces;
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