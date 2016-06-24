import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameRulesTest {

    @Test
    public void itDeterminesIfAMoveCanBeMade() {
        State state = new State();
        GameRules rules = new GameRules(state);

        state.move(new Point(0, 0), Marker.X);

        assertFalse(rules.canMove(state, new Point(0, 0)));
        assertTrue(rules.canMove(state, new Point(1, 0)));
    }

    @Test
    public void itIsNotOverWhenThereAreNoMarkersOnTheBoard() {
        State state = new State();
        GameRules rules = new GameRules(state);
        assertEquals(false, rules.isOver());
    }

    @Test
    public void itIsOverWhenThereIsAWinner() {
        State state = new State();
        GameRules rules = new GameRules(state);

        state.move(new Point(0, 0), Marker.X);
        state.move(new Point(1, 0), Marker.X);
        state.move(new Point(2, 0), Marker.X);

        assertEquals(true, rules.isOver());
    }

    @Test
    public void itCanDetermineWhenOIsTheWinner() {
        State state = new State();
        GameRules rules = new GameRules(state);

        state.move(new Point(0, 0), Marker.X);
        state.move(new Point(1, 0), Marker.O);
        state.move(new Point(2, 0), Marker.X);
        state.move(new Point(1, 1), Marker.O);
        state.move(new Point(2, 2), Marker.X);
        state.move(new Point(1, 2), Marker.O);

        assertEquals(Marker.O, rules.determineWinner());
    }

    @Test
    public void itCanDetermineWhenXIsTheWinner() {
        State state = new State();
        GameRules rules = new GameRules(state);

        state.move(new Point(0, 0), Marker.X);
        state.move(new Point(1, 0), Marker.O);
        state.move(new Point(1, 1), Marker.X);
        state.move(new Point(2, 0), Marker.O);
        state.move(new Point(2, 2), Marker.X);

        assertEquals(Marker.X, rules.determineWinner());
    }

    @Test
    public void itCanDetermineWhenThereIsNoWinner() {
        State state = new State();
        GameRules rules = new GameRules(state);

        state.move(new Point(0, 0), Marker.X);
        state.move(new Point(1, 0), Marker.O);

        assertEquals(null, rules.determineWinner());
    }

    @Test
    public void itIsNotADrawIfThereIsAWinner() {
        State state = new State();
        GameRules rules = new GameRules(state);

        state.move(new Point(0, 0), Marker.X);
        state.move(new Point(1, 0), Marker.O);
        state.move(new Point(1, 1), Marker.X);
        state.move(new Point(2, 0), Marker.O);
        state.move(new Point(2, 2), Marker.X);

        assertEquals(false, rules.isDraw());
    }

    @Test
    public void itIsNotADrawIfTheBoardIsFullAndThereIsAWinner() {
        State state = new State();
        GameRules rules = new GameRules(state);

        state.move(new Point(0, 0), Marker.X);
        state.move(new Point(2, 0), Marker.O);
        state.move(new Point(0, 2), Marker.X);
        state.move(new Point(0, 1), Marker.O);
        state.move(new Point(1, 1), Marker.X);
        state.move(new Point(2, 2), Marker.X);
        state.move(new Point(1, 0), Marker.X);
        state.move(new Point(1, 2), Marker.O);
        state.move(new Point(2, 1), Marker.X);

        assertEquals(false, rules.isDraw());
    }

    @Test
    public void itIsADrawIfThereIsNoWinnerAndNoMoreSpaces() {
        State state = new State();
        GameRules rules = new GameRules(state);

        state.move(new Point(0, 0), Marker.X);
        state.move(new Point(2, 0), Marker.O);
        state.move(new Point(0, 2), Marker.X);
        state.move(new Point(0, 1), Marker.O);
        state.move(new Point(1, 1), Marker.X);
        state.move(new Point(2, 2), Marker.O);
        state.move(new Point(1, 0), Marker.X);
        state.move(new Point(1, 2), Marker.O);
        state.move(new Point(2, 1), Marker.X);

        assertEquals(true, rules.isDraw());
    }
    
}
