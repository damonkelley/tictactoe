package me.damonkelley.tictactoe_app.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import me.damonkelley.io.converters.SpaceIDConverter;
import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe_app.R;
import me.damonkelley.tictactoe_app.adapter.BoardAdapter;
import me.damonkelley.tictactoe_app.factory.PresetFactory;
import me.damonkelley.tictactoe_app.loop.Loop;
import me.damonkelley.tictactoe_app.wrapper.BoardViewWrapper;
import me.damonkelley.tictactoe_app.wrapper.GameViews;
import me.damonkelley.tictactoe_app.wrapper.MessageViewWrapper;

public class GameActivity extends AppCompatActivity {

    private Game game;
    private Loop loop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Marker marker = getFirstMarker();
        String preset = getPreset();

        game = new Game(marker);

        GridView boardView = getBoardView();

        GameViews gameViews = new GameViews()
                .add(new MessageViewWrapper(game, getGameMessageView()))
                .add(new BoardViewWrapper(boardView));

        loop = PresetFactory.presetFor(preset)
                .withFirstMarker(marker)
                .withSecondMarker(marker.opposite())
                .withUpdater(gameViews)
                .withGame(game)
                .build();

        boardView.setOnItemClickListener(advanceLoop());
    }

    @NonNull
    private GridView getBoardView() {
        GridView boardView = (GridView) findViewById(R.id.game);
        boardView.setAdapter(new BoardAdapter(this, game.getBoard()));
        return boardView;
    }

    @NonNull
    private AdapterView.OnItemClickListener advanceLoop() {
        return (adapterView, view, i, l) -> {
            SpaceIDConverter converter = new SpaceIDConverter(3, 3);
            loop.next(converter.convert(i + 1));
        };
    }

    private TextView getGameMessageView() {
        return (TextView) findViewById(R.id.game_message);
    }

    private String getPreset() {
        return getIntent().getStringExtra("preset");
    }

    private Marker getFirstMarker() {
        return Marker.valueOf(getIntent().getStringExtra("first-marker"));
    }

}
