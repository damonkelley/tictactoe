import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class UI implements Finder {
    private final String ERASE_SCREEN = "\033[2J";
    private final String CURSOR_HOME = "\033[H";
    private final String NEW_LINE = "\n";

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
        return new SpaceIDConverter(3, 3).convert(getParsedUserInput());
    }

    private int getParsedUserInput() {
        String input;
        do input = read(); while (!validate(input));

        return parse(input);
    }

    private boolean validate(String input) {
        if (new IntegerRangeInputValidator(1, 9).isValid(input))
            return true;
        else {
            message(input + " is not a valid space");
            return false;
        }
    }

    private int parse(String input) {
        return Integer.parseInt(input);
    }

    public void message(String contents) {
        write(contents + NEW_LINE);
    }

    public void start() {
        new GameLoop(game, this).play();
    }

    public void render() {
        clearScreen();
        write(new GamePresenter(game).present());
    }

    private void clearScreen() {
        write(ERASE_SCREEN + CURSOR_HOME);
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
