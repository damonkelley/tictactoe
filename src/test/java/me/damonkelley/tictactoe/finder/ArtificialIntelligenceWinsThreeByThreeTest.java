package me.damonkelley.tictactoe.finder;

import me.damonkelley.fake.FakeUI;
import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.GameLoop;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Player;
import me.damonkelley.tictactoe.Players;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertFalse;

@RunWith(Parameterized.class)
public class ArtificialIntelligenceWinsThreeByThreeTest {
    private Player computerPlayer;
    private Player randomPlayer;

    public ArtificialIntelligenceWinsThreeByThreeTest(int n) {}

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
        Game game = new Game(computerPlayer.getMarker());
        Players players = new Players(computerPlayer, randomPlayer);

        new GameLoop(new FakeUI()).play(players, game);

        assertFalse("The opponent never wins", game.isWinner(randomPlayer.getMarker()));
    }

    @Test
    public void whenItMovesSecond() {
        Game game = new Game(randomPlayer.getMarker());
        Players players = new Players(computerPlayer, randomPlayer);

        new GameLoop(new FakeUI()).play(players, game);

        assertFalse("The opponent never wins", game.isWinner(randomPlayer.getMarker()));
    }
}