package me.damonkelley.tictactoe_app.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.TextView;
import me.damonkelley.io.converters.SpaceIDConverter;
import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe_app.adapter.BoardAdapter;
import me.damonkelley.tictactoe_app.R;
import me.damonkelley.tictactoe_app.loop.Loop;
import me.damonkelley.tictactoe_app.loop.LoopBuilder;
import me.damonkelley.tictactoe_app.turn.AsyncComputerTurn;
import me.damonkelley.tictactoe_app.turn.MultiPlayerHumanTurn;
import me.damonkelley.tictactoe_app.turn.SinglePlayerHumanTurn;
import me.damonkelley.tictactoe_app.turn.Turn;
import me.damonkelley.tictactoe_app.wrapper.BoardViewWrapper;
import me.damonkelley.tictactoe_app.wrapper.GameViews;
import me.damonkelley.tictactoe_app.wrapper.MessageViewWrapper;

public class GameActivity extends AppCompatActivity {

    private Game game;
    private GameViews gameViews;
    private GridView boardView;
    private TextView gameMessageView;

    private String playerOneType;
    private String playerTwoType;

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

        playerOneType = this.getIntent().getStringExtra("player-one-type");
        playerTwoType = this.getIntent().getStringExtra("player-two-type");

        Loop loop = makeLoop();

        boardView.setOnItemClickListener((adapterView, view, i, l) -> {
            SpaceIDConverter converter = new SpaceIDConverter(3, 3);
            loop.next(converter.convert(i+1));
        });
    }

    private TextView getGameMessageView() {
        return (TextView) this.findViewById(R.id.game_message);
    }

    private Turn createTurnForPlayerType(String type) {
        if ("Computer".equals(type)) {
            return new AsyncComputerTurn();
        } else {
            return new SinglePlayerHumanTurn();
        }
    }

    private Loop makeLoop() {
        if ("Human".equals(playerOneType) && "Human".equals(playerTwoType)) {
            return new LoopBuilder()
                    .withFirstTurn(new MultiPlayerHumanTurn())
                    .withSecondTurn(new MultiPlayerHumanTurn())
                    .withFirstMarker(Marker.X)
                    .withSecondMarker(Marker.O)
                    .withUpdater(gameViews)
                    .withGame(game)
                    .build();
        } else {
            return new LoopBuilder()
                    .withFirstTurn(createTurnForPlayerType(playerOneType))
                    .withSecondTurn(createTurnForPlayerType(playerTwoType))
                    .withFirstMarker(Marker.X)
                    .withSecondMarker(Marker.O)
                    .withUpdater(gameViews)
                    .withGame(game)
                    .build();
        }
    }

    @NonNull
    private GridView getBoardView() {
        GridView boardView = (GridView) this.findViewById(R.id.game);
        boardView.setAdapter(new BoardAdapter(this, game.getBoard()));
        return boardView;
    }
}
