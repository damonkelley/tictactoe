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
        private int type;

        public static final int HUMAN_VS_HUMAN = 0;
        public static final int HUMAN_VS_COMPUTER = 1;
        public static final int COMPUTER_VS_HUMAN = 2;

        public LoopBuilder withHumanTurn(Turn turn) {
            humanTurn = turn;
            return this;
        }

        public LoopBuilder withComputerTurn(Turn turn) {
            computerTurn = turn;
            return this;
        }

        public LoopBuilder withGameType(int type) {
            this.type = type;
            return this;
        }

        public Loop build() {
            switch (type) {
                case HUMAN_VS_COMPUTER:
                    return new Loop(humanTurn, computerTurn);
                case COMPUTER_VS_HUMAN:
                    return new Loop(computerTurn, humanTurn);
                default:
                    return new Loop(humanTurn, humanTurn);
            }
        }
    }
}
