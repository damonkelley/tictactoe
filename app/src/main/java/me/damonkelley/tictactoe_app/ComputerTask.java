package me.damonkelley.tictactoe_app;

import android.os.AsyncTask;
import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.finder.ArtificialIntelligenceFinder;

class ComputerTask extends AsyncTask<Game, Object, GameViews> {

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
}
