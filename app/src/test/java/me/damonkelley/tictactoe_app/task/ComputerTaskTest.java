package me.damonkelley.tictactoe_app.task;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe_app.helpers.LoggingUpdater;
import me.damonkelley.tictactoe_app.wrapper.GameViews;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ComputerTaskTest {
    @Test
    public void itMakesAMoveInTheBackground() {
        Game game = new Game(Marker.X);
        ComputerTask task = new ComputerTask(Marker.X, new GameViews());

        task.doInBackground(game);

        assertNotEquals(game, new Game(Marker.X));
    }

    @Test
    public void itWillWinTheGame() {
        Game game = new Game(Marker.X);
        ComputerTask task = new ComputerTask(Marker.O, new GameViews());

        game.move(new Space(0, 0), Marker.X);
        task.doInBackground(game);

        game.move(new Space(0, 1), Marker.X);
        task.doInBackground(game);

        game.move(new Space(1, 0), Marker.X);
        task.doInBackground(game);

        assertEquals(true, game.isWinner(Marker.O));
    }

    @Test
    public void itHandlesIllegalMoves() {
        Game game = new Game(Marker.X);
        ComputerTask task = new ComputerTask(Marker.O, new GameViews());

        task.doInBackground(game);
        task.doInBackground(game);
    }

    @Test
    public void itUpdatesTheViewsPostExecute() {
        LoggingUpdater updater = new LoggingUpdater();
        new ComputerTask(Marker.O, updater).onPostExecute(updater);
        assertEquals("updated ", updater.log);
    }
}
