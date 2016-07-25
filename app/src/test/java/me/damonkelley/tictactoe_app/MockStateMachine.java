package me.damonkelley.tictactoe_app;

class MockStateMachine implements StateMachine {
    private StringBuffer log;
    private String logMessage;

    MockStateMachine(StringBuffer log, String logMessage) {
        this.log = log;
        this.logMessage = logMessage;
    }

    @Override
    public void next() {
        log.append(logMessage + " ");
    }
}
