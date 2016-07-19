package me.damonkelley.tictactoe_app;

import android.widget.GridView;

public class BoardViewWrapper implements ViewWrapper {
    private GridView view;

    public BoardViewWrapper(GridView view) {
        this.view = view;
    }

    @Override
    public void update() {
        view.invalidateViews();
    }
}
