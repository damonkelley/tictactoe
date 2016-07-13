package me.damonkelley.tictactoe;

import me.damonkelley.tictactoe.finder.Finder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PlayerTest {
    @Test
    public void itUsesAMoveFinderToFindAMove() {
        Player player = new Player(Marker.O, new FakeFinder());
        Game game = new Game(Marker.O);

        player.move(game);
        player.move(game);

        assertEquals(player.getMarker(), game.getBoard().get(new Space(2, 2)));
    }

    @Test
    public void equalityIsBasedOnMarkerAndFinderType() {
        assertEquals(new Player(Marker.O, null), new Player(Marker.O, null));
        assertEquals(new Player(Marker.O, new FakeFinder()), new Player(Marker.O, new FakeFinder()));

        assertNotEquals(new Player(Marker.X, new FakeFinder()), new Player(Marker.O, new FakeFinder()));
        assertNotEquals(new Player(Marker.O, null), new Player(Marker.O, new FakeFinder()));
        assertNotEquals(new Player(Marker.X, null), new Player(Marker.O, null));
    }

    private class FakeFinder extends Finder {
        public Space getNextMove(Game game) {
            return new Space(2, 2);
        }
    }
}
