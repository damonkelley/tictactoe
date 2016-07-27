package me.damonkelley.tictactoe;

public enum Marker {
    X,
    O;

    public Marker opposite() {
        return (this == Marker.X) ? Marker.O : Marker.X;
    }
}
