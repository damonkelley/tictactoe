import java.util.Arrays;

public class PlayAgainMenu {
    private final UI ui;
    private String playAgain;

    public PlayAgainMenu(UI ui) {
        this.ui = ui;
    }

    private String promptToPlayAgain() {
        Validator validator = new Validator() {
            @Override
            public boolean isValid(String input) {
                if (Arrays.asList("Y", "N").contains(input)) {
                    return true;
                }
                return false;
            }
        };

        return ui.prompt("Play again?\nY/N: ", validator);
    }

    public boolean shouldPlayAgain() {
        return playAgain.equals("Y");
    }

    public PlayAgainMenu display() {
        playAgain = promptToPlayAgain();
        return this;
    }
}
