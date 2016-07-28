package me.damonkelley.tictactoe_app.task;

import android.os.AsyncTask;
import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.IllegalMoveException;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.finder.ArtificialIntelligenceFinder;
import me.damonkelley.tictactoe_app.wrapper.UserInterfaceUpdater;

public class ComputerTask extends AsyncTask<Game, Object, UserInterfaceUpdater> {

    private Marker marker;
    private UserInterfaceUpdater updater;

    public ComputerTask(Marker marker, UserInterfaceUpdater updater) {
        this.marker = marker;
        this.updater = updater;
    }

    @Override
    protected UserInterfaceUpdater doInBackground(Game... games) {
        Game game = games[0];
        move(game);
        return this.updater;
    }

    private void move(Game game) {
        try {
            game.move(new ArtificialIntelligenceFinder(marker).getNextMove(game), marker);
        } catch (IllegalMoveException e) {}
    }

    @Override
    protected void onPostExecute(UserInterfaceUpdater updater) {
        updater.update();
    }

    public void exec(Game game) {
        this.execute(game);
    }
}
