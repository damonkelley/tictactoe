public class HumanPlayer extends Player {
    private Finder finder;

    public HumanPlayer(Marker marker, Finder finder) {
        super(marker);
        this.finder = finder;
    }

    @Override
    public void move(State state) {
        state.move(finder.getNextMove(), getMarker());
    }
}
