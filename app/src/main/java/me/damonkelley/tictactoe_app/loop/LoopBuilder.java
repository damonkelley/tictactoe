package me.damonkelley.tictactoe_app.loop;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe_app.turn.Turn;

public class LoopBuilder {

    private Turn first;
    private Turn second;

    public LoopBuilder withFirstTurn(Turn turn) {
        this.first = turn;
        return this;
    }

    public LoopBuilder withSecondTurn(Turn second) {
        this.second = second;
        return this;
    }

    public LoopBuilder withFirstMarker(Marker marker) {
        first.setMarker(marker);
        return this;
    }

    public LoopBuilder withSecondMarker(Marker marker) {
        second.setMarker(marker);
        return this;
    }

    public LoopBuilder withGame(Game game) {
        this.first.setGame(game);
        this.second.setGame(game);
        return this;
    }

    public Loop build() {
        this.first.setNext(second);
        this.second.setNext(first);
        Loop loop = new Loop().setNext(first);

        this.first.initialize();
        return loop;
    }
}
