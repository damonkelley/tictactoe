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

        return marker == player.marker;
    }

    @Override
    public String toString() {
        return "Player{marker=" + marker + '}';
    }
}
