import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
    @Test
    public void itUsesAMoveFinderToFindAMove() {
        Player player = new Player(Marker.O, new FakeFinder());
        Game game = new Game(player, new Player(Marker.X, new FakeFinder()));

        player.move(game);
        player.move(game);

        assertEquals(player.getMarker(), game.getBoard().get(new Space(2, 2)));
    }

    private class FakeFinder implements Finder {
        public Space getNextMove(Game game) {
            return new Space(2, 2);
        }
    }
}
