public abstract class Player {
    protected Marker marker;

    public Player(Marker marker) {
        this.marker = marker;
    }

    abstract public void move(State state);

    public Marker getMarker() {
        return marker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return marker == player.marker;
    }

    @Override
    public String toString() {
        return "Player{marker=" + marker + '}';
    }
}
