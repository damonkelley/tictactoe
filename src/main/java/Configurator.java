public class Configurator {
    private UI ui;

    public Configurator(UI ui) {
        this.ui = ui;
    }

    public Game configure() {
        Player xPlayer = getPlayerTypeFor(Marker.X);
        Player oPlayer = getPlayerTypeFor(Marker.O);
        Marker first = getFirstMarker();

        if (first == Marker.X) {
            return new Game(xPlayer, oPlayer);
        } else {
            return new Game(oPlayer, xPlayer);
        }
    }

    private Player getPlayerTypeFor(Marker marker) {
        PlayerTypeInputValidator validator = new PlayerTypeInputValidator();

        String type = ui.prompt(String.format("Is %s a human or a computer? H/C", marker), validator);

        if (type.equals(validator.HUMAN_TYPE)) {
            return PlayerFactory.human(marker, ui);
        } else {
            return PlayerFactory.computer(marker);
        }
    }

    private Marker getFirstMarker() {
        String input = ui.prompt("Who will go first? X/O", new MarkerInputValidator());
        return Marker.valueOf(input);
    }
}
