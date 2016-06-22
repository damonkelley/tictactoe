public class HumanPlayer extends Player {
    private UI ui;

    public HumanPlayer(Marker marker, UI ui) {
        super(marker);
        this.ui = ui;
    }

    @Override
    public void move(State state) {
        state.move(ui.getNextMove(), getMarker());
    }
}
