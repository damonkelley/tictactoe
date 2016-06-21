import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ComputerPlayerAlwaysWinsTest {
    private ComputerPlayer computerPlayer;
    private Player player;
    private State state;
    private Board board;
    private Random generator = new Random();

    public ComputerPlayerAlwaysWinsTest(int n) {
    }

    @Parameterized.Parameters(name = "Computer Always Wins")
    public static List<Integer> parameters() {
        return IntStream.range(0, 20).boxed().collect(Collectors.toList());
    }

    @Before
    public void setUp() throws Exception {
        computerPlayer = new ComputerPlayer(PlayerMarker.X);
        player = new Player(PlayerMarker.O);

        state = new State();
        board = state.getBoard();
    }

    @Test
    public void whenItMovesFirst() {
        while (!state.isOver()) {
            computerPlayer.move(state);

            if (!state.isOver()) {
                int spaceIndex = generator.nextInt(board.availableSpaces().size());
                player.move(state, board.availableSpaces().get(spaceIndex));
            }
        }

        boolean notALoss = state.getWinner() == computerPlayer.getMarker() || state.isDraw();
        assertTrue(notALoss);
    }


    @Test
    public void whenItMovesSecond() {
        while (!state.isOver()) {
            int spaceIndex = generator.nextInt(board.availableSpaces().size());
            player.move(state, board.availableSpaces().get(spaceIndex));

            if (!state.isOver()) computerPlayer.move(state);
        }

        boolean notALoss = state.getWinner() == computerPlayer.getMarker() || state.isDraw();
        assertTrue(notALoss);
    }
}