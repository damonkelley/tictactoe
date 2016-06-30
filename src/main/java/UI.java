import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class UI {
    private final String ERASE_SCREEN = "\033[2J";
    private final String CURSOR_HOME = "\033[H";
    private final String NEW_LINE = "\n";

    private BufferedReader reader;
    private BufferedWriter writer;

    public UI(BufferedReader reader, BufferedWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public String getUserInput() {
        String input = read();

        if (isEndOfFile(input)) quit();

        return input;
    }

    private boolean isEndOfFile(String input) {
        return input == null;
    }

    private void quit() {
        throw new GameException("Goodbye");
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

    public String prompt(String contents, Validator validator) {
        String input;

        do {
            write(contents);
            input = getUserInput();
        } while (!validator.isValid(input));

        return input;
    }
}
