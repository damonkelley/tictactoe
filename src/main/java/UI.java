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

        Player player1 = new Player(Marker.O, this);
        Player player2 = new Player(Marker.X, new ArtificialIntelligenceFinder(Marker.X));
        this.game =  new Game(player1, player2);
    }

    public Space getNextMove(State state) {
        switch (parseUserInput()) {
            case 1:
                return new Space(0, 0);
            case 2:
                return new Space(1, 0);
            case 3:
                return new Space(2, 0);
            case 4:
                return new Space(0, 1);
            case 5:
                return new Space(1, 1);
            case 6:
                return new Space(2, 1);
            case 7:
                return new Space(0, 2);
            case 8:
                return new Space(1, 2);
            case 9:
                return new Space(2, 2);
            default:
                return new Space(0, 0);
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
        writer.write(new GamePresenter(game).present());
        writer.flush();
    }

    private void clearScreen() throws IOException {
        writer.write("\033[2J");
        writer.write("\033[H");
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
