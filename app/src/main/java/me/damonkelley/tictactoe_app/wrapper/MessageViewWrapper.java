package me.damonkelley.tictactoe_app.wrapper;

import android.widget.TextView;
import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe_app.R;

public class MessageViewWrapper implements ViewWrapper {
    private final Game game;
    private final TextView view;

    public MessageViewWrapper(Game game, TextView view) {
        this.game = game;
        this.view = view;
    }

    public void update() {
        if (game.isOver()) {
            view.setText(getGameMessage());
        }
    }

    private int getGameMessage() {
        if (game.hasWinner()) {
            return getGameWinnerText();
        } else {
            return R.string.draw;
        }
    }

    private int getGameWinnerText() {
        if (game.isWinner(Marker.X)) {
            return R.string.x_wins;
        } else {
            return R.string.o_wins;
        }
    }
}
