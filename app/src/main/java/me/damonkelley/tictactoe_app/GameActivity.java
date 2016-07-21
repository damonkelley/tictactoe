package me.damonkelley.tictactoe_app;

import android.os.Bundle;
import android.preference.PreferenceManager;
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

        String gameType = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("game_type", "human-vs-human");

        Loop loop = new Loop.LoopBuilder()
                .withComputerTurn(new RunnableTurn(() -> new ComputerTask(gameViews).execute(game)))
                .withHumanTurn(new NullTurn())
                .withGameType(gameType)
                .build();

        boardView.setOnItemClickListener((adapterView, view, i, l) -> {
            SpaceIDConverter converter = new SpaceIDConverter(3, 3);
            new RunnableTurn(new HumanTurnRunnable(converter.convert(i+1), game)).go(loop);
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
