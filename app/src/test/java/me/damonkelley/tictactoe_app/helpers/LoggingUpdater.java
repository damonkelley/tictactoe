package me.damonkelley.tictactoe_app.helpers;

import me.damonkelley.tictactoe_app.wrapper.UserInterfaceUpdater;

public class LoggingUpdater implements UserInterfaceUpdater {
    public String log = "";

    @Override
    public void update() {
        log += "updated ";
    }
}
