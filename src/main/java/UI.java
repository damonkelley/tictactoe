import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class UI implements Finder {
    private final String ERASE_SCREEN = "\033[2J";
    private final String CURSOR_HOME = "\033[H";
    private final String NEW_LINE = "\n";

    private BufferedReader reader;
    private BufferedWriter writer;

    public UI(BufferedReader reader, BufferedWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public Space getNextMove(Game game) {
        return new SpaceIDConverter(3, 3).convert(getParsedUserInput());
    }

    private int getParsedUserInput() {
        return parse(getUserInput());
    }

    private int parse(String input) {
        return Integer.parseInt(input);
    }

    private String getUserInput() {
        String input;

        do {
            input = read();
            if (isEndOfFile(input)) quit();
        } while (!validate(input));

        return input;
    }

    private boolean isEndOfFile(String input) {
        return input == null;
    }

    private boolean quit() {
        throw new GameException("Goodbye");
    }

    private boolean validate(String input) {
        if (!new IntegerRangeInputValidator(1, 9).isValid(input)) {
            message(input + " is not a valid space");
            return false;
        }
        return true;
    }

    public void message(String contents) {
        write(contents + NEW_LINE);
    }

    public void render(Game game) {
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
