import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertNotEquals;

@RunWith(Parameterized.class)
public class ArtificialIntelligenceAlwaysWinsTest {
    private Player computerPlayer;
    private Player randomPlayer;

    public ArtificialIntelligenceAlwaysWinsTest(int n) {}

    @Parameterized.Parameters(name = "Computer Always Wins")
    public static List<Integer> parameters() {
        return IntStream.range(0, 20).boxed().collect(Collectors.toList());
    }

    @Before
    public void setUp() throws Exception {
        randomPlayer = new Player(Marker.O, new RandomFinder());
        computerPlayer = new Player(Marker.X, new ArtificialIntelligenceFinder(Marker.X));
    }

    @Test
    public void whenItMovesFirst() {
        Game game = new Game(computerPlayer, randomPlayer);
        while (!game.isOver()) game.nextMove();

        assertNotEquals(randomPlayer, game.getWinner());
    }

    @Test
    public void whenItMovesSecond() {
        Game game = new Game(randomPlayer, computerPlayer);
        while (!game.isOver()) game.nextMove();

        assertNotEquals(randomPlayer, game.getWinner());
    }

    private class RandomFinder implements Finder {
        private Random generator = new Random();

        @Override
        public Space getNextMove(State state) {
            int index = generator.nextInt(state.getBoard().availableSpaces().size());
            return state.getBoard().availableSpaces().get(index);
        }
    }
}