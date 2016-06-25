import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ArtificialIntelligenceFinderTest {
    @Test
    public void itWillChooseTheWinningSpaceIfThereIsOne() {
        Game game = new Game(new Player(Marker.X, null), new Player(Marker.O, null));

        game.getState().move(new Space(0, 0), Marker.X);
        game.getState().move(new Space(2, 0), Marker.O);
        game.getState().move(new Space(1, 1), Marker.X);
        game.getState().move(new Space(0, 2), Marker.O);

        ArtificialIntelligenceFinder finder = new ArtificialIntelligenceFinder(Marker.X);

        assertEquals(new Space(2, 2), finder.getNextMove(game));
    }

    @Test
    public void itWillPreventALoss() {
        Game game = new Game(new Player(Marker.X, null), new Player(Marker.O, null));

        game.getState().move(new Space(0, 0), Marker.X);
        game.getState().move(new Space(2, 0), Marker.O);
        game.getState().move(new Space(1, 1), Marker.X);

        ArtificialIntelligenceFinder finder = new ArtificialIntelligenceFinder(Marker.O);

        assertEquals(new Space(2, 2), finder.getNextMove(game));
    }
}
