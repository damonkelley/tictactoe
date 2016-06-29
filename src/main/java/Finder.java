abstract class Finder {
    abstract Space getNextMove(Game game);

    public boolean equals(Object o) {
        if (o != null && getClass() == o.getClass()) return true;
        return false;
    }
}
