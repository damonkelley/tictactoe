package me.damonkelley.tictactoe_app.factory;

import me.damonkelley.tictactoe_app.loop.LoopBuilder;
import me.damonkelley.tictactoe_app.turn.AsyncComputerTurn;
import me.damonkelley.tictactoe_app.turn.MultiPlayerHumanTurn;
import me.damonkelley.tictactoe_app.turn.SinglePlayerHumanTurn;

public class PresetFactory {

    private static final String COMPUTER_VS_HUMAN = "computer-vs-human";
    private static final String HUMAN_VS_COMPUTER = "human-vs-computer";
    private static final String HUMAN_VS_HUMAN = "human-vs-human";

    public static LoopBuilder presetFor(String preset) {
        switch(preset) {
            case COMPUTER_VS_HUMAN:
                return computerVsHuman();
            case HUMAN_VS_COMPUTER:
                return humanVsComputer();
            case HUMAN_VS_HUMAN:
                return humanVsHuman();
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
