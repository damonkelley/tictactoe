package me.damonkelley.tictactoe;

public class GameBuilder {
    private Marker firstMarker;
    private Player xPlayer;
    private Player oPlayer;

    public GameBuilder setFirstMarker(Marker marker) {
        this.firstMarker = marker;
        return this;
    }

    public Marker getFirstMarker() {
        return this.firstMarker;
    }

    public GameBuilder setXPlayer(Player player) {
        this.xPlayer = player;
        return this;
    }

    public GameBuilder setOPlayer(Player player) {
        this.oPlayer = player;
        return this;
    }

    public Player getXPlayer() {
        return xPlayer;
    }

    public Player getOPlayer() {
        return oPlayer;
    }

    public Game build() {
        if (getFirstMarker() == Marker.X) {
            return new Game(getXPlayer(), getOPlayer());
        } else {
            return new Game(getOPlayer(), getXPlayer());
        }
    }
}
