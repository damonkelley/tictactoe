package me.damonkelley.tictactoe_app;

import java.util.ArrayList;
import java.util.List;

class GameViews {
    private final List<ViewWrapper> wrappers;

    public GameViews() {
        wrappers = new ArrayList<>();
    }

    public GameViews add(ViewWrapper wrapper) {
        wrappers.add(wrapper);
        return this;
    }

    public void update() {
        wrappers.forEach(ViewWrapper::update);
    }
}
