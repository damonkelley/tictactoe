package me.damonkelley.tictactoe_app.turn;

import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe_app.loop.StateMachine;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SinglePlayerHumanTurnTest {

    private FakeStateMachine machine;
    private Game game;
    private Turn turn;

    @Before
    public void setUp() throws Exception {
        machine = new FakeStateMachine();
        game = new Game(Marker.X);

        turn = new SinglePlayerHumanTurn()
                .setGame(game)
                .setLoop(machine)
                .setMarker(Marker.X);
    }

    @Test
    public void itMakesMoves() {
        turn.go(new Space(0, 1));
        assertEquals(Marker.X, game.getBoard().get(new Space(0, 1)));
    }

    @Test
    public void itTransitionsTheMachineToTheNextState() {
        turn.go(new Space(0, 1));
        assertEquals("set-next next ", machine.log);
    }

    @Test
    public void itWillNotMakeAnIllegalMove() {
        game.move(new Space(0, 1), Marker.X);
        turn.go(new Space(0, 1));
        assertEquals(Marker.X, game.getBoard().get(new Space(0, 1)));
    }

    @Test
    public void itDoesNotAdvanceTheLoopDuringInitialization() {
        machine.setNext(turn);

        turn.setLoop(machine);
        turn.initialize();

        assertEquals("set-next ", machine.log);
    }

    @Test
    public void itWillNotTransitionTheLoopToTheNextStateWithAnIllegalMove() {
        game.move(new Space(0, 1), Marker.X);
        turn.go(new Space(0, 1));
        assertEquals("", machine.log);
    }

    class FakeStateMachine implements StateMachine {
        public String log = "";

        @Override
        public void next(Space space) {
            log += "next ";
        }

        @Override
        public FakeStateMachine setNext(Turn turn) {
            log += "set-next ";
            return this;
        }
    }

}
