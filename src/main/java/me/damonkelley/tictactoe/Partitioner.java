package me.damonkelley.tictactoe;

import me.damonkelley.tictactoe.layout.FourByFourLayout;
import me.damonkelley.tictactoe.layout.Layout;
import me.damonkelley.tictactoe.layout.ThreeByThreeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Partitioner {
    private Board board;

    public Partitioner(Board board) {
        this.board = board;
    }

    public List<List<Marker>> partition() {
        List<List<Marker>> partitions = new ArrayList<>();

        getLayout().all().forEach(list -> {
            partitions.add(getAll(list));
        });

        return partitions;
    }

    private Layout getLayout() {
        if (board.getSize() == 4) {
            return new FourByFourLayout();
        } else {
            return new ThreeByThreeLayout();
        }
    }

    private List<Marker> getAll(List<Space> list) {
        return list.stream()
                .map(e -> board.get(e))
                .collect(Collectors.toList());
    }

}
