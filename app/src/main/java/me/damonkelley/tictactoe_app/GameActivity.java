package me.damonkelley.tictactoe_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.TextView;
import me.damonkelley.io.converters.SpaceIDConverter;
import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;

public class GameActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game = new Game(Marker.X);

        TextView gameMessage = (TextView) this.findViewById(R.id.game_message);
        GridView gameView = (GridView) this.findViewById(R.id.game);

        gameView.setAdapter(new BoardAdapter(this, game.getBoard()));

        gameView.setOnItemClickListener((adapterView, view, i, l) -> {
            game.move(new SpaceIDConverter(3, 3).convert(i + 1), game.nextTurn());
            gameView.invalidateViews();
            if (game.isOver()) {
                if (game.hasWinner()) {
                    Marker winner = (game.isWinner(Marker.X)) ? Marker.X : Marker.O;
                    gameMessage.setText(String.format("%s wins!", winner));
                } else {
                    gameMessage.setText("Draw");
                }
            }
        });
    }
}
