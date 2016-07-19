package me.damonkelley.tictactoe_app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameLoopMachineTest {
    private String log = "";

    @Test
    public void oneMoveIsMade() {
        new GameLoopMachine(new MockTurn("one"), new MockTurn("two"));

        assertEquals("one ", log);
    }

    @Test
    public void twoMovesAreMade() {
        GameLoopMachine machine = new GameLoopMachine(new MockTurn("one"), new MockTurn("two"));
        machine.next();

        assertEquals("one two ", log);
    }

    @Test
    public void threeMovesAreMade() {
        GameLoopMachine machine = new GameLoopMachine(new MockTurn("one"), new MockTurn("two"));

        machine.next();
        machine.next();

        assertEquals("one two one ", log);
    }

    @Test
    public void anInfiniteLoopCannotBeCreated() {
        Turn turn = new Turn() {
            @Override
            public void go(StateMachine machine) {
                log += "one ";
                machine.next();
            }
        };

        new GameLoopMachine(turn, new MockTurn("two"));

        assertEquals("one two ", log);
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
