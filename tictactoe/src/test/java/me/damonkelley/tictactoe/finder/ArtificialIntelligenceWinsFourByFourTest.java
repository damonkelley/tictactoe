package me.damonkelley.tictactoe.finder;

import me.damonkelley.fake.FakeUI;
import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.GameLoop;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Player;
import me.damonkelley.tictactoe.Players;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class ArtificialIntelligenceWinsFourByFourTest {
    private Player computerPlayer;
    private Player randomPlayer;

    @Before
    public void setUp() throws Exception {
        randomPlayer = new Player(Marker.O, new RandomFinder());
        computerPlayer = new Player(Marker.X, new ArtificialIntelligenceFinder(Marker.X));
    }
    @Test
    public void whenItMovesFirstInA4By4Game() {
        Game game = new Game(computerPlayer.getMarker(), 4);
        Players players = new Players(computerPlayer, randomPlayer);

        new GameLoop(new FakeUI()).play(players, game);

        assertFalse("The opponent never wins", game.isWinner(randomPlayer.getMarker()));
    }

    @Test
    public void whenItMovesSecondInA4By4Game() {
        Game game = new Game(randomPlayer.getMarker(), 4);
        Players players = new Players(computerPlayer, randomPlayer);

        new GameLoop(new FakeUI()).play(players, game);

        assertFalse("The opponent never wins", game.isWinner(randomPlayer.getMarker()));
    }
}
