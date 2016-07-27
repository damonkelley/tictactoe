package me.damonkelley.tictactoe_app.adapter;

import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;
import me.damonkelley.tictactoe.Board;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe.Space;
import me.damonkelley.tictactoe_app.activity.GameActivity;
import me.damonkelley.tictactoe_app.rule.GameActivityTestRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class BoardAdapterTest {

    @Rule
    public ActivityTestRule<GameActivity> mActivityRule = new GameActivityTestRule();

    private Board board;
    private BoardAdapter adapter;
    private GameActivity mActivity;

    @Before
    public void setUp() throws Exception {
        board = new Board();
        mActivity = mActivityRule.getActivity();
        adapter = new BoardAdapter(mActivity, board);
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
        BoardAdapter adapter = new BoardAdapter(mActivity, board);

        board.put(new Space(3, 3), Marker.X);

        assertEquals(Marker.X, adapter.getItem(15));
        assertEquals(null, adapter.getItem(1));
    }

    @Test
    public void theCount() {
        assertEquals(9, new BoardAdapter(mActivity, new Board()).getCount());
        assertEquals(16, new BoardAdapter(mActivity, new Board(4)).getCount());
    }

    @Test
    public void allItemIdsAreZero() {
        assertEquals(0, adapter.getItemId(1));
        assertEquals(0, adapter.getItemId(2));
        assertEquals(0, adapter.getItemId(3));
    }

    @Test
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
