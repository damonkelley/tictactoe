package me.damonkelley.tictactoe;

import me.damonkelley.tictactoe.finder.ArtificialIntelligenceFinder;
import me.damonkelley.tictactoe.finder.HumanFinder;
import me.damonkelley.ui.UI;

public class PlayerFactory {
    public static Player computer(Marker marker) {
        return new Player(marker, new ArtificialIntelligenceFinder(marker));
    }

    public static Player human(Marker marker, UI ui) {
        return new Player(marker, new HumanFinder(ui));
    }
}
