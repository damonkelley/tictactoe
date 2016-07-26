package me.damonkelley.tictactoe_app.turn;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe_app.StateMachine;

public abstract class Turn {
    protected Turn next;
    protected Game game;
    protected Marker marker;
    protected StateMachine loop;

    public Turn setLoop(StateMachine loop) {
        this.loop = loop;
        return this;
    }

    public Turn setMarker(Marker marker) {
        this.marker = marker;
        return this;
    }

    public Turn setNext(Turn next) {
        this.next = next;
        return this;
    }

    public Turn setGame(Game game) {
        this.game = game;
        return this;
    }

    public abstract void go(Space space);
}
