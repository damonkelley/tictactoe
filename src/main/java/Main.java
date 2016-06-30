import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        UI ui = new UI(reader, writer);

        try {
            new GameLoop(new Configurator(ui).configure(), ui).play();
        } catch (GameException e) {
            ui.message(e.getMessage());
        }
    }
}
