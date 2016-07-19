package me.damonkelley.tictactoe_app;

import android.os.AsyncTask;
import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.finder.ArtificialIntelligenceFinder;

class ComputerTask extends AsyncTask<Game, Object, GameViews> {

    private GameViews views;

    public ComputerTask(GameViews views) {
        this.views = views;
    }

    @Override
    protected GameViews doInBackground(Game... games) {
        Game game = games[0];
        game.move(new ArtificialIntelligenceFinder(game.nextTurn()).getNextMove(game), game.nextTurn());
        return this.views;
    }

    @Override
    protected void onPostExecute(GameViews gameViews) {
        gameViews.update();
    }
}
