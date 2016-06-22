public abstract class Player {
    protected Marker marker;

    public Player(Marker marker) {
        this.marker = marker;
    }

    abstract public void move(State state);

    public Marker getMarker() {
        return marker;
    }
}
