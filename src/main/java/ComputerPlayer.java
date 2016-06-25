public class ComputerPlayer extends Player {

    public ComputerPlayer(Marker marker) {
        super(marker, new ArtificialIntelligenceFinder(marker));
    }

    public void move(State state) {
        Finder finder = new ArtificialIntelligenceFinder(getMarker());
        state.move(finder.getNextMove(state), getMarker());
    }

}
