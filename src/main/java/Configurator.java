public class Configurator {
    private UI ui;

    public Configurator(UI ui) {
        this.ui = ui;
    }

    public Game configure() {
        return new GameBuilder()
                .setXPlayer(getPlayerTypeFor(Marker.X))
                .setOPlayer(getPlayerTypeFor(Marker.O))
                .setFirstMarker(getFirstMarker())
                .build();
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
