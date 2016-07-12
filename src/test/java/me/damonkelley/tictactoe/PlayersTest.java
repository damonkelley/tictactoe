package me.damonkelley.tictactoe;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class PlayersTest {
    @Test
    public void itGetsAPlayerByMarker() {
        Player playerA = new Player(Marker.X, null);
        Player playerB = new Player(Marker.O, null);

        Players players = new Players(playerA, playerB);
        assertSame(playerA, players.get(Marker.X));
        assertSame(playerB, players.get(Marker.O));
    }
}
