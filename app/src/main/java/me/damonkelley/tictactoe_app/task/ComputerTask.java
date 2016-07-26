package me.damonkelley.tictactoe_app.task;

import android.os.AsyncTask;
import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.finder.ArtificialIntelligenceFinder;
import me.damonkelley.tictactoe_app.wrapper.GameViews;

public class ComputerTask extends AsyncTask<Game, Object, GameViews> {

    private Marker marker;
    private GameViews views;

    public ComputerTask(Marker marker, GameViews views) {
        this.marker = marker;
        this.views = views;
    }

    @Override
    protected GameViews doInBackground(Game... games) {
        Game game = games[0];
        game.move(new ArtificialIntelligenceFinder(marker).getNextMove(game), marker);
        return this.views;
    }

    @Override
    protected void onPostExecute(GameViews gameViews) {
        gameViews.update();
    }

    public void exec(Game game) {
        this.execute(game);
    }
}
