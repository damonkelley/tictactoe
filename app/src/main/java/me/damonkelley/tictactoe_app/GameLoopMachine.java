package me.damonkelley.tictactoe_app;

class GameLoopMachine implements StateMachine {
    private final Turn one;
    private final Turn two;
    private Turn next;

    public GameLoopMachine(Turn one, Turn two) {
        this.one = one;
        this.two = two;
        this.next = one;
    }

    public void next() {
        next.go(this);
        next = (next == one) ? two : one;
    }
}
