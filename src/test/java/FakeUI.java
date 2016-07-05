import java.util.ArrayList;

class FakeUI extends UI {
    public String log = "";

    public ArrayList<String> input = new ArrayList<>();

    public FakeUI() {
        super(null, null);
    }

    @Override
    public String getUserInput() {
        return input.remove(0);
    }

    @Override
    protected void write(String input) {
        log += input + " ";
    }

    @Override
    public void render(Game game) {
        write("render");
    }

    @Override
    public void message(String contents) {
        write(contents);
    }
}
