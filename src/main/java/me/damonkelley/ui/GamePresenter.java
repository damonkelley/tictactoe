package me.damonkelley.ui;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;

import java.util.ArrayList;

class GamePresenter {
    private Game game;
    private int id = 1;

    private final String THREE_BY_THREE = " %s | %s | %s \n" +
                                          "---+---+---\n" +
                                          " %s | %s | %s \n" +
                                          "---+---+---\n" +
                                          " %s | %s | %s \n" +
                                          "\n";

   private final String FOUR_BY_FOUR =    " %s | %s | %s | %s \n" +
                                          "-----+-----+-----+-----\n" +
                                          " %s | %s | %s | %s \n"  +
                                          "-----+-----+-----+-----\n" +
                                          " %s | %s | %s | %s \n"  +
                                          "-----+-----+-----+-----\n" +
                                          " %s | %s | %s | %s \n"  +
                                          "\n";

    public GamePresenter(Game game) {
        this.game = game;
    }

    public String present() {
        String template = (game.getBoard().getSize() == 4)? FOUR_BY_FOUR : THREE_BY_THREE;
        return addMarkersToTemplate(template, getMarkersFromBoard());
    }

    private String addMarkersToTemplate(String template, ArrayList<String> markers) {
        return String.format(template, markers.toArray());
    }

    private ArrayList<String> getMarkersFromBoard() {
        ArrayList<String> markers = new ArrayList<>();

        game.getBoard().forEach(space -> {
            MarkerPresenter presenter = new MarkerPresenter(id, game.getBoard().get(space));
            markers.add(center(presenter.present()));
            id++;
        });

        return markers;
    }

    private String center(String s) {
        int width = calculateSpaceWidth();
        int paddingLeft = calculateLeftPadding(s, width);
        int paddingRight = calculateRightPadding(s, width);

        s = padLeft(s, paddingLeft);
        s = padRight(s, paddingRight);
        return s;
    }

    private int calculateRightPadding(String s, int width) {
        if (hasEvenLength(s)) width--;
        return width;
    }

    private int calculateLeftPadding(String s, int width) {
        int padding = s.length() + (width - s.length()) / 2;
        if (hasEvenLength(s)) padding++;
        return padding;
    }

    private boolean hasEvenLength(String s) {
        return s.length() % 2 == 0;
    }

    private int calculateSpaceWidth() {
        int width = game.getBoard().getSize() / 2;
        return (width % 2 == 0)? width + 1 : width;
    }

    private String padRight(String s, int width) {
        return String.format("%-" + width  + "s", s);
    }

    private String padLeft(String s, int padStart) {
        return String.format("%" + padStart + "s", s);
    }

    private class MarkerPresenter {
        private Integer id;
        private Marker marker;

        public MarkerPresenter(Integer id, Marker marker) {
            this.id = id;
            this.marker = marker;
        }

        public String present() {
            return (marker != null) ? marker.toString() : id.toString();
        }
    }
}
