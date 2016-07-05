import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameBuilderTest {
    @Test
    public void itCanBuildAGameWhereXIsFirst() {
        GameBuilder builder = new GameBuilder()
                .setXPlayer(PlayerFactory.human(Marker.X, null))
                .setOPlayer(PlayerFactory.computer(Marker.O))
                .setFirstMarker(Marker.X);


        Game expectedGame = new Game(PlayerFactory.human(Marker.X, null), PlayerFactory.computer(Marker.O));
        assertEquals(expectedGame, builder.build());
    }

    @Test
    public void itCanBuildAGameWhereOIsFirst() {
        GameBuilder builder = new GameBuilder()
                .setXPlayer(PlayerFactory.human(Marker.X, null))
                .setOPlayer(PlayerFactory.computer(Marker.O))
                .setFirstMarker(Marker.O);


        Game expectedGame = new Game(PlayerFactory.computer(Marker.O), PlayerFactory.human(Marker.X, null));
        assertEquals(expectedGame, builder.build());
    }
}
