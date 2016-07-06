package me.damonkelley.ui;

import me.damonkelley.io.validators.MarkerInputValidator;
import me.damonkelley.io.validators.PlayerTypeInputValidator;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Player;
import me.damonkelley.tictactoe.PlayerFactory;

public class GameConfigurationMenu {
    private final String FIRST_MARKER_MESSAGE = "Who will go first?\nX/O: ";
    private final String PLAYER_TYPE_MESSAGE_TEMPLATE = "Is %s a human (H) or a computer (C)?\nH/C: ";

    private Player xPlayer;
    private Player oPlayer;
    private Marker firstMarker;

    private UI ui;

    private final PlayerTypeInputValidator validator;

    public GameConfigurationMenu(UI ui) {
        xPlayer = PlayerFactory.human(Marker.X, ui);
        oPlayer = PlayerFactory.computer(Marker.O);
        firstMarker = Marker.X;

        validator = new PlayerTypeInputValidator();

        this.ui = ui;
    }

    public GameConfigurationMenu display() {
        promptXPlayerType();
        promptOPlayerType();
        promptForFirstMarker();

        return this;
    }

    private Marker promptForFirstMarker() {
        String input = ui.prompt(FIRST_MARKER_MESSAGE, new MarkerInputValidator());
        firstMarker = Marker.valueOf(input);
        return firstMarker;
    }

    private void promptXPlayerType() {
        xPlayer = makePlayer(promptPlayerTypeFor(Marker.X), Marker.X);
    }

    private void promptOPlayerType() {
        oPlayer = makePlayer(promptPlayerTypeFor(Marker.O), Marker.O);
    }

    private Player makePlayer(String type, Marker marker) {
        if (type.equals(validator.HUMAN_TYPE)) {
            return PlayerFactory.human(marker, ui);
        } else {
            return PlayerFactory.computer(marker);
        }
    }

    private String promptPlayerTypeFor(Marker marker) {
        PlayerTypeInputValidator validator = new PlayerTypeInputValidator();
        return ui.prompt(String.format(PLAYER_TYPE_MESSAGE_TEMPLATE, marker), validator);
    }

    public Player getXPlayer() {
        return xPlayer;
    }

    public Player getOPlayer() {
        return oPlayer;
    }

    public Marker getFirstMarker() {
        return firstMarker;
    }
}
