package me.damonkelley.tictactoe_app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameLoopMachineTest {
    private String log = "";

    @Test
    public void oneMoveIsMade() {
        GameLoopMachine machine = new GameLoopMachine(new MockTurn("one"), new MockTurn("two"));
        machine.next();
        
        assertEquals("one ", log);
    }

    @Test
    public void twoMovesAreMade() {
        GameLoopMachine machine = new GameLoopMachine(new MockTurn("one"), new MockTurn("two"));
        machine.next();
        machine.next();

        assertEquals("one two ", log);
    }

    @Test
    public void threeMovesAreMade() {
        GameLoopMachine machine = new GameLoopMachine(new MockTurn("one"), new MockTurn("two"));
        machine.next();
        machine.next();
        machine.next();

        assertEquals("one two one ", log);
    }

    private class MockTurn implements Turn {
        private String message;

        MockTurn(String message) {
            this.message = message;
        }

        @Override
        public void go(StateMachine machine) {
            log += message + " ";
        }
    }

}
