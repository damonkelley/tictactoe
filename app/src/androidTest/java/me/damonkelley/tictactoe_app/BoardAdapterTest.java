package me.damonkelley.tictactoe_app;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.mock.MockContext;
import android.widget.TextView;
import me.damonkelley.tictactoe.Board;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Space;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class BoardAdapterTest {

    private Board board;
    private BoardAdapter adapter;

    @Before
    public void setUp() throws Exception {
        board = new Board();
        adapter = new BoardAdapter(InstrumentationRegistry.getContext(), board);
    }

    @Test
    public void itGetsAViewAtThePosition() {
        board.put(new Space(0, 0), Marker.X);

        assertEquals(Marker.X, adapter.getItem(0));
        assertEquals(null, adapter.getItem(1));
    }

    @Test
    public void itGetsAViewAtThePositionForA4By4Board() {
        Board board = new Board(4);
        BoardAdapter adapter = new BoardAdapter(new MockContext(), board);

        board.put(new Space(3, 3), Marker.X);

        assertEquals(Marker.X, adapter.getItem(15));
        assertEquals(null, adapter.getItem(1));
    }

    @Test
    public void theCount() {
        assertEquals(9, new BoardAdapter(new MockContext(), new Board()).getCount());
        assertEquals(16, new BoardAdapter(new MockContext(), new Board(4)).getCount());
    }

    @Test
    public void allItemIdsAreZero() {
        assertEquals(0, adapter.getItemId(1));
        assertEquals(0, adapter.getItemId(2));
        assertEquals(0, adapter.getItemId(3));
    }

    @Test
    @Ignore(value = "Unable to make the Resource available for inflation during testing")
    public void viewsAreTextViewsCorrespondingToBoardSpaces() {
        board.put(new Space(1, 1), Marker.O);
        board.put(new Space(1, 0), Marker.X);

        TextView view = (TextView) adapter.getView(0, null, null);
        assertEquals("", view.getText());

        view = (TextView) adapter.getView(4, null, null);
        assertEquals("O", view.getText());

        view = (TextView) adapter.getView(1, null, null);
        assertEquals("X", view.getText());
    }
}
