package me.damonkelley.tictactoe_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.TextView;
import me.damonkelley.io.converters.SpaceIDConverter;
import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;

public class GameActivity extends AppCompatActivity {

    private Game game;
    private TextView gameMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game = new Game(Marker.X);
        gameMessage = (TextView) this.findViewById(R.id.game_message);

        createGameView().setOnItemClickListener((adapterView, view, i, l) -> {
            game.move(new SpaceIDConverter(3, 3).convert(i + 1), game.nextTurn());

            updateGameBoard((GridView) adapterView);
            updateGameMessage();
        });
    }

    @NonNull
    private GridView createGameView() {
        GridView gameView = (GridView) this.findViewById(R.id.game);
        gameView.setAdapter(new BoardAdapter(this, game.getBoard()));
        return gameView;
    }

    private void updateGameBoard(GridView gameView) {
        gameView.invalidateViews();
    }

    private void updateGameMessage() {
        if (game.isOver()) {
            getGameMessage();
        }
    }

    private void getGameMessage() {
        if (game.hasWinner()) {
            gameMessage.setText(getGameWinnerText());
        } else {
            gameMessage.setText(R.string.draw);
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
