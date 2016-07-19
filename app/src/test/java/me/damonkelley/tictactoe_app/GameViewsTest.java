package me.damonkelley.tictactoe_app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameViewsTest {
    private String log = "";

    @Test
    public void itUpdatesTheUI() {
        GameViews gameView = new GameViews();
        gameView.add(new BoardViewWrapperMock())
                .add(new MessageViewWrapperMock());

        gameView.update();
        assertEquals("update-board update-message ", log);
    }

    private class MessageViewWrapperMock extends MessageViewWrapper {
        MessageViewWrapperMock() {
            super(null, null);
        }

        public void update() {
            log += "update-message ";
        }
    }

    private class BoardViewWrapperMock extends BoardViewWrapper {
        BoardViewWrapperMock() {
            super(null);
        }

        public void update() {
            log += "update-board ";
        }
    }
}
