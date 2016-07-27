package me.damonkelley.tictactoe_app.factory;

import me.damonkelley.tictactoe_app.loop.LoopBuilder;
import me.damonkelley.tictactoe_app.turn.AsyncComputerTurn;
import me.damonkelley.tictactoe_app.turn.MultiPlayerHumanTurn;
import me.damonkelley.tictactoe_app.turn.SinglePlayerHumanTurn;

public class PresetFactory {
    public static LoopBuilder presetFor(String preset) {
        switch(preset) {
            case "computer-vs-human":
                return computerVsHuman();
            case "human-vs-computer":
                return humanVsComputer();
            default:
                return humanVsHuman();
        }
    }

    private static LoopBuilder humanVsComputer() {
        return new LoopBuilder()
                .withFirstTurn(new SinglePlayerHumanTurn())
                .withSecondTurn(new AsyncComputerTurn());
    }

    private static LoopBuilder computerVsHuman() {
        return new LoopBuilder()
                .withFirstTurn(new AsyncComputerTurn())
                .withSecondTurn(new SinglePlayerHumanTurn());
    }

    private static LoopBuilder humanVsHuman() {
        return new LoopBuilder()
                .withFirstTurn(new MultiPlayerHumanTurn())
                .withSecondTurn(new MultiPlayerHumanTurn());
    }
}
