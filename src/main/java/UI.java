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
        this.game = new Game(player1, player2);
    }

    public Space getNextMove(Game game) {
        return convertSpaceIdToSpace(getParsedUserInput());
    }

    private Space convertSpaceIdToSpace(int spaceId) {
        switch (spaceId) {
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

    private int getParsedUserInput() {
        String input;
        do input = read(); while (!validate(input));

        return parse(input);
    }

    private boolean validate(String input) {
        if (new IntegerInputValidator().isValid(input))
            return true;
        else {
            write(input + " is not a valid space\n");
            return false;
        }
    }

    private int parse(String input) {
        return Integer.parseInt(input);
    }

    public void start() {
        render();
        while (!game.isOver()) {
            game.nextMove();
            render();
        }

        write("Game Over\n");
    }

    public void render() {
        clearScreen();
        write(new GamePresenter(game).present());
    }

    private void clearScreen() {
        write("\033[2J\033[H");
    }

    private String read() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.exit(1);
        }
        return null;
    }

    private void write(String input) {
        try {
            writer.write(input);
            writer.flush();
        } catch (IOException e) {
            System.exit(1);
        }
    }
}
