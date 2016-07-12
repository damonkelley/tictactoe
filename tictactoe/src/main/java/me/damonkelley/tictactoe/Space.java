package me.damonkelley.tictactoe;

import java.awt.*;

public class Space extends Point implements Comparable {
    public Space(int x, int y) {
        super(x, y);
    }

    @Override
    public int compareTo(Object o) {
        if (this == o || this.equals(o)) return 0;

        Space space = (Space) o;

        if (this.y > space.y) return 1;
        if (this.y == space.y && this.x > space.x) return 1;
        return -1;
    }
}
