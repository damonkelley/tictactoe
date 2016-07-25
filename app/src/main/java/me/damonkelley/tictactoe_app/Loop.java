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

        private Turn first;
        private Turn second;

        public static final String HUMAN_VS_HUMAN = "human-vs-human";

        public LoopBuilder withFirstTurn(Turn turn) {
            first = turn;
            return this;
        }

        public LoopBuilder withSecondTurn(Turn turn) {
            second = turn;
            return this;
        }
        public Loop build() {
            return new Loop(first, second);
        }
    }
}
