import java.util.ArrayList;

class FakeUI extends UI {
    public String log = "";

    public ArrayList<String> input = new ArrayList<>();

    public FakeUI() {
        super(null, null);
    }

    @Override
    public void render(Game game) {
        log += "render ";
    }

    @Override
    public void message(String contents) {
        log += contents + " ";
    }

    @Override
    public String prompt(String contents, Validator validator) {
        String userInput;

        do {
            log += contents + " ";
            userInput = input.remove(0);
        } while(!validator.isValid(userInput));

        return userInput;
    }
}
