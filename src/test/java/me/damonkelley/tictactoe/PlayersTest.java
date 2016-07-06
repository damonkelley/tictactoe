package me.damonkelley.tictactoe;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PlayersTest {
    @Test
    public void itGetsAPlayerByMarker() {
        Player playerA = new Player(Marker.X, null);
        Player playerB = new Player(Marker.O, null);

        Players players = new Players(playerA, playerB);
        assertTrue("It can get Player A", playerA == players.get(Marker.X));
        assertTrue("It can get Player B", playerB == players.get(Marker.O));
    }
}
