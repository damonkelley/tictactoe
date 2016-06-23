public class ComputerPlayer extends Player {

    public ComputerPlayer(Marker marker) {
        super(marker);
    }

    public void move(State state) {
        Finder finder = new ArtificialIntelligenceFinder(this, state);
        state.move(finder.getNextMove(), getMarker());
    }

}
