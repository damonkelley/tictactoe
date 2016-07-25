package me.damonkelley.tictactoe_app;

import android.test.mock.MockContext;
import android.widget.GridView;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardViewWrapperTest {
    @Test
    public void itUpdatesTheBoardPresentation() {
        MockGridView view = new MockGridView();
        BoardViewWrapper wrapper = new BoardViewWrapper(view);

        wrapper.update();

        assertEquals(true, view.invalidated);
    }

    private class MockGridView extends GridView {
        private boolean invalidated = false;

        public MockGridView() {
            super(new MockContext());
        }

        @Override
        public void invalidateViews() {
            invalidated = true;
        }
    }
}
