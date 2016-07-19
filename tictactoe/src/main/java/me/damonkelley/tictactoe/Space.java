package me.damonkelley.tictactoe;

public class Space implements Comparable {
    private int y;
    private int x;

    public Space(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Space space = (Space) o;

        if (y != space.y) return false;
        return x == space.x;
    }

    @Override
    public int hashCode() {
        int result = y;
        result = 31 * result + x;
        return result;
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
