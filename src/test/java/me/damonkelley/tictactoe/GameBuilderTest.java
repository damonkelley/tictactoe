package me.damonkelley.tictactoe;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameBuilderTest {
    @Test
    public void itCanBuildAGameWhereXIsFirst() {
        GameBuilder builder = new GameBuilder()
                .setXPlayer(PlayerFactory.human(Marker.X, null))
                .setOPlayer(PlayerFactory.computer(Marker.O))
                .setFirstMarker(Marker.X);


        Game expectedGame = new Game(Marker.X);
        assertEquals(expectedGame, builder.build());
    }

    @Test
    public void itCanBuildAGameWhereOIsFirst() {
        GameBuilder builder = new GameBuilder()
                .setXPlayer(PlayerFactory.human(Marker.X, null))
                .setOPlayer(PlayerFactory.computer(Marker.O))
                .setFirstMarker(Marker.O);


        Game expectedGame = new Game(Marker.X);
        assertEquals(expectedGame, builder.build());
    }
}
