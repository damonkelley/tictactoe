import java.util.Arrays;

public class PlayAgainMenu {
    private final UI ui;
    private String playAgain;
    private String reconfigure;

    public PlayAgainMenu(UI ui) {
        this.ui = ui;
    }

    private String promptToPlayAgain() {
        return promptYesOrNo("Play again?");
    }

    private String promptToReconfigure() {
        return promptYesOrNo("Do you want to use a different game configuration?");
    }

    private String promptYesOrNo(String question) {
        Validator validator = new Validator() {
            @Override
            public boolean isValid(String input) {
                if (Arrays.asList("Y", "N").contains(input)) {
                    return true;
                }
                return false;
            }
        };

        return ui.prompt(question + "\nY/N: ", validator);
    }

    public boolean shouldPlayAgain() {
        return playAgain.equals("Y");
    }

    public boolean shouldReconfigure() {
        return reconfigure.equals("Y");
    }

    public PlayAgainMenu display() {
        playAgain = promptToPlayAgain();
        if (shouldPlayAgain()) reconfigure = promptToReconfigure();
        return this;
    }
}
