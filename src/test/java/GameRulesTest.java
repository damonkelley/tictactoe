import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameRulesTest {

    private State state;
    private GameRules rules;

    @Test
    public void itDeterminesIfAMoveCanBeMade() {
        State state = new State();
        GameRules rules = new GameRules(state);

        state.move(new Space(0, 0), Marker.X);

        assertFalse(rules.canMove(state, new Space(0, 0)));
        assertTrue(rules.canMove(state, new Space(1, 0)));
    }

    @Before
    public void setUp() throws Exception {
        state = new State();
        rules = new GameRules(state);
    }

    @Test
    public void itIsNotOverWhenThereAreNoMarkersOnTheBoard() {
        assertEquals(false, rules.isOver());
    }

    @Test
    public void itIsOverWhenThereIsAWinner() {
        state.move(new Space(0, 0), Marker.X);
        state.move(new Space(1, 0), Marker.X);
        state.move(new Space(2, 0), Marker.X);

        assertEquals(true, rules.isOver());
    }

    @Test
    public void itCanDetermineWhenOIsTheWinner() {
        state.move(new Space(0, 0), Marker.X);
        state.move(new Space(1, 0), Marker.O);
        state.move(new Space(2, 0), Marker.X);
        state.move(new Space(1, 1), Marker.O);
        state.move(new Space(2, 2), Marker.X);
        state.move(new Space(1, 2), Marker.O);

        assertEquals(Marker.O, rules.determineWinner());
    }

    @Test
    public void itCanDetermineWhenXIsTheWinner() {
        state.move(new Space(0, 0), Marker.X);
        state.move(new Space(1, 0), Marker.O);
        state.move(new Space(1, 1), Marker.X);
        state.move(new Space(2, 0), Marker.O);
        state.move(new Space(2, 2), Marker.X);

        assertEquals(Marker.X, rules.determineWinner());
    }

    @Test
    public void itCanDetermineWhenThereIsNoWinner() {
        state.move(new Space(0, 0), Marker.X);
        state.move(new Space(1, 0), Marker.O);

        assertEquals(null, rules.determineWinner());
    }

    @Test
    public void itIsNotADrawIfThereIsAWinner() {
        state.move(new Space(0, 0), Marker.X);
        state.move(new Space(1, 0), Marker.O);
        state.move(new Space(1, 1), Marker.X);
        state.move(new Space(2, 0), Marker.O);
        state.move(new Space(2, 2), Marker.X);

        assertEquals(false, rules.isDraw());
    }

    @Test
    public void itIsNotADrawIfTheBoardIsFullAndThereIsAWinner() {
        state.move(new Space(0, 0), Marker.X);
        state.move(new Space(2, 0), Marker.O);
        state.move(new Space(0, 2), Marker.X);
        state.move(new Space(0, 1), Marker.O);
        state.move(new Space(1, 1), Marker.X);
        state.move(new Space(2, 2), Marker.X);
        state.move(new Space(1, 0), Marker.X);
        state.move(new Space(1, 2), Marker.O);
        state.move(new Space(2, 1), Marker.X);

        assertEquals(false, rules.isDraw());
    }

    @Test
    public void itIsADrawIfThereIsNoWinnerAndNoMoreSpaces() {
        state.move(new Space(0, 0), Marker.X);
        state.move(new Space(2, 0), Marker.O);
        state.move(new Space(0, 2), Marker.X);
        state.move(new Space(0, 1), Marker.O);
        state.move(new Space(1, 1), Marker.X);
        state.move(new Space(2, 2), Marker.O);
        state.move(new Space(1, 0), Marker.X);
        state.move(new Space(1, 2), Marker.O);
        state.move(new Space(2, 1), Marker.X);

        assertEquals(true, rules.isDraw());
    }
    
}
