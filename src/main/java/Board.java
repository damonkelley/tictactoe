import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private Map<Point, PlayerMarker> spaces;

    public Board() {
        this.spaces = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.spaces.put(new Point(i, j), null);
            }
        }
    }

    private Board(Map<Point, PlayerMarker> spaces) {
        this.spaces = spaces;
    }

    public PlayerMarker get(Point point) {
        return spaces.get(point);
    }

    public Board put(Point point, PlayerMarker marker) {
        spaces.put(point, marker);
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
        return new Board(new HashMap<Point, PlayerMarker>(this.spaces));
    }

    @Override
    public String toString() {
        PlayerMarker space;
        String marker;
        StringBuffer output = new StringBuffer();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                space = spaces.get(new Point(j, i));
                if (space == null) {
                    marker = " ";
                } else {
                    marker = space.toString();
                }

                if (j < 2) {
                    output.append(" ").append(marker).append(" |");
                } else {
                    output.append(" ").append(marker).append(" \n");
                }

            }
            if (i < 2) {
                output.append("---+---+---\n");
            }
        }
        return output.toString();
    }

    public List<Point> availableSpaces() {
        return this.spaces.entrySet()
                .stream()
                .filter(space -> space.getValue() == null)
                .map(space -> space.getKey())
                .collect(Collectors.toList());
    }

}