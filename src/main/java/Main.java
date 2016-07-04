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
        GameConfigurationMenu menu = new GameConfigurationMenu(ui);

        try {
            menu.display();
            Game game = new GameBuilder()
                    .setXPlayer(menu.getXPlayer())
                    .setOPlayer(menu.getOPlayer())
                    .setFirstMarker(menu.getFirstMarker())
                    .build();

            new GameLoop(game, ui).play();
        } catch (GameException e) {
            ui.message(e.getMessage());
        }
    }
}
