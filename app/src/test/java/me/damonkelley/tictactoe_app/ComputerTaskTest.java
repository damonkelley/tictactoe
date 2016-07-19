package me.damonkelley.tictactoe_app;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Space;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ComputerTaskTest {
    @Test
    public void itMakesAMoveInTheBackground() {
        Game game = new Game(Marker.X);
        ComputerTask task = new ComputerTask(new GameViews());

        task.doInBackground(game);

        assertNotEquals(game, new Game(Marker.X));
    }

    @Test
    public void itWillWinTheGame() {
        Game game = new Game(Marker.X);
        ComputerTask task = new ComputerTask(new GameViews());

        game.move(new Space(0, 0), Marker.X);
        task.doInBackground(game);

        game.move(new Space(0, 1), Marker.X);
        task.doInBackground(game);

        game.move(new Space(1, 0), Marker.X);
        task.doInBackground(game);

        assertEquals(true, game.isWinner(Marker.O));
    }

    @Test
    public void itUpdatesTheViewsPostExecute() {
        MockGameViews views = new MockGameViews();
        new ComputerTask(views).onPostExecute(views);
        assertEquals("update", views.log);
    }

    private class MockGameViews extends GameViews {
        public String log = "";
        @Override
        public void update() {
            log += "update";
        }
    }

}
