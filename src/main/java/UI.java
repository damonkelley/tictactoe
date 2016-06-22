import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class UI {
    private Game game;
    private BufferedReader reader;
    private BufferedWriter writer;
    private final HumanPlayer player1;
    private final ComputerPlayer player2;

    public UI(BufferedReader reader, BufferedWriter writer) {
        this.reader = reader;
        this.writer = writer;
        player1 = new HumanPlayer(Marker.O, this);
        player2 = new ComputerPlayer(Marker.X);

        this.game = new Game(player1, player2);
    }

    public Point getNextMove() {
        switch (parseUserInput()) {
            case 1:
                return new Point(0, 0);
            case 2:
                return new Point(0, 1);
            case 3:
                return new Point(0, 2);
            case 4:
                return new Point(1, 0);
            case 5:
                return new Point(1, 1);
            case 6:
                return new Point(1, 2);
            case 7:
                return new Point(2, 0);
            case 8:
                return new Point(2, 1);
            case 9:
                return new Point(2, 2);
            default:
                return new Point(0, 0);
        }
    }

    private int parseUserInput() {
        try {
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return 0;
    }

    public void render() throws IOException {
        StringBuffer output = formatBoard();

        writer.write(output.toString());
        writer.flush();
    }

    private StringBuffer formatBoard() {
        StringBuffer output = new StringBuffer();
        Board board = game.getBoard();

        int index = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                Marker marker = board.get(new Point(i, j));
                if (j < 2) {
                    output.append(" ")
                            .append((marker != null) ? marker.toString() : index)
                            .append(" |");
                } else {
                    output.append(" ")
                            .append((marker != null) ? marker.toString() : index)
                            .append(" \n");
                }

                index++;
            }
            if (i < 2) {
                output.append("---+---+---\n");
            }
        }
        output.append("\n");

        return output;
    }

    public void start() throws IOException {
        render();
        while (!game.isOver()) {
            game.nextMove();
            render();
        }
    }
}
