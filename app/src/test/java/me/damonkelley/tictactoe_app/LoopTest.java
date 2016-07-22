package me.damonkelley.tictactoe_app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoopTest {
    private String log = "";

    @Test
    public void oneMoveIsMade() {
        new Loop(new MockTurn("one"), new MockTurn("two"));

        assertEquals("one ", log);
    }

    @Test
    public void twoMovesAreMade() {
        Loop machine = new Loop(new MockTurn("one"), new MockTurn("two"));
        machine.next();

        assertEquals("one two ", log);
    }

    @Test
    public void threeMovesAreMade() {
        Loop machine = new Loop(new MockTurn("one"), new MockTurn("two"));

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

        new Loop(turn, new MockTurn("two"));

        assertEquals("one two ", log);
    }

    @Test
    public void itBuildsAHumanVsHumanGame() {
        Loop loop = new Loop.LoopBuilder()
                .withHumanTurn(new MockTurn("human"))
                .withComputerTurn(new MockTurn("computer"))
                .withGameType(Loop.LoopBuilder.HUMAN_VS_HUMAN)
                .build();

        loop.next();
        assertEquals("human human ", log);
    }

    @Test
    public void itBuildsAHumanVsComputerGame() {
        Loop loop = new Loop.LoopBuilder()
                .withHumanTurn(new MockTurn("human"))
                .withComputerTurn(new MockTurn("computer"))
                .withGameType(Loop.LoopBuilder.HUMAN_VS_COMPUTER)
                .build();

        loop.next();
        assertEquals("human computer ", log);
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
