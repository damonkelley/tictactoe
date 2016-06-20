import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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

}