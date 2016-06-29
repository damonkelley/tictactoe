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

        Player player1 = new Player(Marker.O, new HumanFinder(ui));
        Player player2 = new Player(Marker.X, new ArtificialIntelligenceFinder(Marker.X));

        Game game = new Game(player1, player2);

        new GameLoop(game, ui).play();
    }
}
