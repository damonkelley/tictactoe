package me.damonkelley.ui;

import me.damonkelley.io.validators.IntegerRangeInputValidator;
import me.damonkelley.io.validators.MarkerInputValidator;
import me.damonkelley.io.validators.PlayerTypeInputValidator;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Player;
import me.damonkelley.tictactoe.PlayerFactory;

public class GameConfigurationMenu {
    private final String FIRST_MARKER_MESSAGE = "Who will go first?\nX/O: ";
    private final String PLAYER_TYPE_MESSAGE_TEMPLATE = "Is %s a human (H) or a computer (C)?\nH/C: ";
    private final String BOARD_SIZE_MESSAGE = "How large is the board? 3x3 (3) or 4x4 (4)?\n3/4: ";

    private Player xPlayer;
    private Player oPlayer;
    private Marker firstMarker;
    private int boardSize;

    private UI ui;

    private final PlayerTypeInputValidator validator;

    public GameConfigurationMenu(UI ui) {
        xPlayer = PlayerFactory.human(Marker.X, ui);
        oPlayer = PlayerFactory.computer(Marker.O);
        boardSize = 3;
        firstMarker = Marker.X;

        validator = new PlayerTypeInputValidator();

        this.ui = ui;
    }

    public GameConfigurationMenu display() {
        promptForBoardSize();
        promptXPlayerType();
        promptOPlayerType();
        promptForFirstMarker();

        return this;
    }

    private void promptForBoardSize() {
       boardSize = Integer.parseInt(ui.prompt(BOARD_SIZE_MESSAGE, new IntegerRangeInputValidator(3, 4)));
    }

    private void promptForFirstMarker() {
        String input = ui.prompt(FIRST_MARKER_MESSAGE, new MarkerInputValidator());
        firstMarker = Marker.valueOf(input);
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

    public int getBoardSize() {
        return boardSize;
    }
}
