package me.damonkelley.tictactoe_app;

public class Loop implements StateMachine {
    private final Turn one;
    private final Turn two;
    private Turn next;

    public Loop(Turn one, Turn two) {
        this.one = one;
        this.two = two;
        this.next = one;

        next();
    }

    public void next() {
        Turn turn = next;
        next = (next == one) ? two : one;
        turn.go(this);
    }

    public static class LoopBuilder {

        private Turn humanTurn;
        private Turn computerTurn;
        private String type;

        public static final String HUMAN_VS_COMPUTER = "human-vs-computer";
        public static final String HUMAN_VS_HUMAN = "human-vs-human";

        public LoopBuilder withHumanTurn(Turn turn) {
            humanTurn = turn;
            return this;
        }

        public LoopBuilder withComputerTurn(Turn turn) {
            computerTurn = turn;
            return this;
        }

        public LoopBuilder withGameType(String type) {
            this.type = type;
            return this;
        }

        public Loop build() {
            if (HUMAN_VS_COMPUTER.equals(type)) {
                return new Loop(humanTurn, computerTurn);
            } else {
                return new Loop(humanTurn, humanTurn);
            }
        }
    }
}
