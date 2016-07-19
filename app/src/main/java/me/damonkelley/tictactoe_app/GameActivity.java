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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game = new Game(Marker.X);

        TextView gameMessage = (TextView) this.findViewById(R.id.game_message);
        GridView boardView = createBoardView();

        GameViews gameViews = new GameViews()
                .add(new MessageViewWrapper(game, gameMessage))
                .add(new BoardViewWrapper(boardView));

        boardView.setOnItemClickListener((adapterView, view, i, l) -> {
            game.move(new SpaceIDConverter(3, 3).convert(i + 1), game.nextTurn());
            gameViews.update();
        });
    }

    @NonNull
    private GridView createBoardView() {
        GridView boardView = (GridView) this.findViewById(R.id.game);
        boardView.setAdapter(new BoardAdapter(this, game.getBoard()));
        return boardView;
    }
}
