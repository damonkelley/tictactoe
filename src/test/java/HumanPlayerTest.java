import org.junit.Test;

import java.awt.*;
import java.io.*;

import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {
    @Test
    public void itGetsItsMovesFromTheUI() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("1\n".getBytes())));

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new ByteArrayOutputStream()));
        HumanPlayer player = new HumanPlayer(Marker.X, new UI(reader, writer));

        State state = new State();
        player.move(state);

        assertEquals(player.getMarker(), state.getBoard().get(new Point(0, 0)));
    }
}
