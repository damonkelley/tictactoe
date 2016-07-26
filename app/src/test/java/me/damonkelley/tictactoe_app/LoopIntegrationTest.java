package me.damonkelley.tictactoe_app;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe.finder.ArtificialIntelligenceFinder;
import me.damonkelley.tictactoe.finder.Finder;
import me.damonkelley.tictactoe_app.turn.HumanTurn;
import me.damonkelley.tictactoe_app.turn.Turn;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoopIntegrationTest {

    private class ComputerTurn extends Turn {

        @Override
        public void go(Space space) {
            Finder finder = new ArtificialIntelligenceFinder(marker);
            game.move(finder.getNextMove(game), marker);
            loop.setNext(next);
        }
    }

    @Test
    public void itCanPlayAGameToTheEnd() {
        Game game = new Game(Marker.X);

        Loop loop = new LoopBuilder()
                .withFirstTurn(new HumanTurn())
                .withSecondTurn(new ComputerTurn())
                .withFirstMarker(Marker.X)
                .withSecondMarker(Marker.O)
                .withGame(game)
                .build();

        loop.next(new Space(0, 0));
        loop.next(new Space(1, 0));
        loop.next(new Space(0, 1));

        assertEquals(true, game.isWinner(Marker.O));
    }
}
