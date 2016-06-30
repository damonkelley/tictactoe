public class Configurator {
    private UI ui;

    public Configurator(UI ui) {
        this.ui = ui;
    }

    public Game configure() {
        Player xPlayer = askPlayerTypeFor(Marker.X);
        Player oPlayer = askPlayerTypeFor(Marker.O);
        Marker first = askFirstMarker();

        if (first == Marker.X) {
            return new Game(xPlayer, oPlayer);
        } else {
            return new Game(oPlayer, xPlayer);
        }
    }

    private Player askPlayerTypeFor(Marker marker) {
        ui.message(String.format("Is %s a human or a computer? H/C", marker));

        if (ui.getUserInput().equals("H")) {
            return PlayerFactory.human(marker, ui);
        }

        return PlayerFactory.computer(marker);
    }

    private Marker askFirstMarker() {
        ui.message(String.format("Who will go first? X/O"));

        if (ui.getUserInput().equals("X")) {
            return Marker.X;
        }

        return Marker.O;
    }
}
