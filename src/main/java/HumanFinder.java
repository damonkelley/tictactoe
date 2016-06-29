public class HumanFinder extends Finder {
    private UI ui;

    public HumanFinder(UI ui) {
        this.ui = ui;
    }

    @Override
    public Space getNextMove(Game game) {
        return new SpaceIDConverter(3, 3).convert(getParsedUserInput());
    }

    private int getParsedUserInput() {
        return parse(getUserInput());
    }

    private String getUserInput() {
        String input;

        do {
            input = ui.getUserInput();
        } while (!validate(input));

        return input;
    }

    private int parse(String input) {
        return Integer.parseInt(input);
    }

    private boolean validate(String input) {
        if (new IntegerRangeInputValidator(1, 9).isValid(input)) {
            return true;
        } else {
            throw new InputValidationError(input + " is not a valid space");
        }
    }
}
