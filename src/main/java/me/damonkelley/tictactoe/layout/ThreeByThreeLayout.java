package me.damonkelley.tictactoe.layout;

import me.damonkelley.tictactoe.Space;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class ThreeByThreeLayout implements Layout{

    public List<List<Space>> all() {
        List<List<Space>> layouts = new ArrayList<>();

        layouts.addAll(getRows());
        layouts.addAll(getColumns());
        layouts.addAll(getDiagonals());

        return layouts;
    }

    private List<List<Space>> getRows() {
        return asList(
                asList(new Space(0, 0), new Space(1, 0), new Space(2, 0)),
                asList(new Space(0, 1), new Space(1, 1), new Space(2, 1)),
                asList(new Space(0, 2), new Space(1, 2), new Space(2, 2))
        );
    }

    private List<List<Space>> getColumns() {
        return asList(
                asList(new Space(0, 0), new Space(0, 1), new Space(0, 2)),
                asList(new Space(1, 0), new Space(1, 1), new Space(1, 2)),
                asList(new Space(2, 0), new Space(2, 1), new Space(2, 2))
        );
    }
    private List<List<Space>> getDiagonals() {
        return asList(
                asList(new Space(0, 0), new Space(1, 1), new Space(2, 2)),
                asList(new Space(0, 2), new Space(1, 1), new Space(2, 0))
        );
    }
}
