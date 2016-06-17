import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionalGameTest {
    @Test
    public void emptyBoardIsWrittenToOutput() {
        String input = "";
        StringBuffer output = new StringBuffer();
        Game game = new Game(input, output);
        game.play();

        String expected = "   |   |   \n---+---+---\n   |   |   \n---+---+---\n   |   |   \n";
        assertEquals(expected, output.toString());
    }

    @Test
    public void singlePositionIsReadFromInputAndAddedToTheBoard() {
        String input = "0\n";
        StringBuffer output = new StringBuffer();

        Game game = new Game(input, output);
        game.play();

        String expected = " X |   |   \n---+---+---\n   |   |   \n---+---+---\n   |   |   \n";
        assertEquals(expected, output.toString());
    }

    @Test
    public void multiplePositionsAreReadFromInputAndAddedToTheBoard() {
        String input = "0\n2\n";
        StringBuffer output = new StringBuffer();

        Game game = new Game(input, output);
        game.play();

        String expected = " X |   | O \n---+---+---\n   |   |   \n---+---+---\n   |   |   \n";
        assertEquals(expected, output.toString());
    }
    
    @Test
    public void xWinsTheGame() {
        String input = "0\n1\n4\n2\n8\n";
        StringBuffer output = new StringBuffer();

        Game game = new Game(input, output);
        game.play();

        assertEquals(game.getWinner(), Player.X);
    }

    @Test
    public void oWinsTheGame() {
        String input = "0\n1\n2\n4\n8\n7\n";
        StringBuffer output = new StringBuffer();

        Game game = new Game(input, output);
        game.play();

        assertEquals(game.getWinner(), Player.O);
    }
}
