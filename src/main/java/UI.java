import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class UI implements Finder {
    private Game game;
    private BufferedReader reader;
    private BufferedWriter writer;

    public UI(BufferedReader reader, BufferedWriter writer) {
        this.reader = reader;
        this.writer = writer;

        this.game = new Game(new HumanPlayer(Marker.O, this), new ComputerPlayer(Marker.X));
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
        clearScreen();
        writer.write(formatBoard());
        writer.flush();
    }

    private void clearScreen() throws IOException {
        writer.write("\033[2J");
        writer.write("\033[H");
    }

    private String formatBoard() {
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

        return output.toString();
    }

    public void start() throws IOException {
        render();
        while (!game.isOver()) {
            game.nextMove();
            render();
        }

        writer.write("Game Over\n");
        writer.flush();
    }
}
