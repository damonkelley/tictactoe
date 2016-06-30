public class Player {
    protected Marker marker;
    private Finder finder;

    public Player(Marker marker, Finder finder) {
        this.marker = marker;
        this.finder = finder;
    }

    public void move(Game game) {
        game.getState().move(finder.getNextMove(game), getMarker());
    }

    public Marker getMarker() {
        return marker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (marker != player.marker) return false;
        return finder != null ? finder.equals(player.finder) : player.finder == null;

    }

    @Override
    public String toString() {
        return "Player{" +
                "marker=" + marker +
                ", finder=" + finder +
                '}';
    }
}
