package me.damonkelley.tictactoe;

import java.util.Arrays;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(Player playerA, Player playerB) {
        players = Arrays.asList(playerA, playerB);
    }

    public Player get(Marker marker) {
        return players.stream()
                .filter(player -> player.getMarker() == marker)
                .findFirst()
                .get();
    }
}
