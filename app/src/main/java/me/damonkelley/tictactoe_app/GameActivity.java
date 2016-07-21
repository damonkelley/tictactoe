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
    private GameViews gameViews;
    private GridView boardView;
    private TextView gameMessageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game = new Game(Marker.X);
        boardView = getBoardView();
        gameMessageView = getGameMessageView();

        gameViews = new GameViews()
                .add(new MessageViewWrapper(game, gameMessageView))
                .add(new BoardViewWrapper(boardView));

        Loop loop = makeLoop();

        boardView.setOnItemClickListener((adapterView, view, i, l) -> {
            SpaceIDConverter converter = new SpaceIDConverter(3, 3);
            new RunnableTurn(new HumanTurnRunnable(converter.convert(i+1), Marker.X, game)).go(loop);
            gameViews.update();
        });
    }

    private TextView getGameMessageView() {
        return (TextView) this.findViewById(R.id.game_message);
    }

    private Loop makeLoop() {
        String gameType = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("game_type", Loop.LoopBuilder.HUMAN_VS_HUMAN);

        RunnableTurn computerTurn = new RunnableTurn(() -> {
            new ComputerTask(gameViews).execute(game);
        });

        return new Loop.LoopBuilder()
                .withComputerTurn(computerTurn)
                .withHumanTurn(new NullTurn())
                .withGameType(gameType)
                .build();
    }

    @NonNull
    private GridView getBoardView() {
        GridView boardView = (GridView) this.findViewById(R.id.game);
        boardView.setAdapter(new BoardAdapter(this, game.getBoard()));
        return boardView;
    }
}
