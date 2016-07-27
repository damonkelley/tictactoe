package me.damonkelley.tictactoe_app.factory;

import me.damonkelley.tictactoe_app.loop.LoopBuilder;
import me.damonkelley.tictactoe_app.turn.AsyncComputerTurn;
import me.damonkelley.tictactoe_app.turn.MultiPlayerHumanTurn;
import me.damonkelley.tictactoe_app.turn.SinglePlayerHumanTurn;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class PresetFactoryTest {

    @Test
    public void itCreatesAHumanVsHumanLoopBuilder() {
        LoopBuilder loopBuilder = PresetFactory.presetFor("human-vs-human");
        assertThat(loopBuilder.getFirstTurn(), instanceOf(MultiPlayerHumanTurn.class));
        assertThat(loopBuilder.getSecondTurn(), instanceOf(MultiPlayerHumanTurn.class));
    }

    @Test
    public void itCreatesAComputerVsHumanLoopBuilder() {
        LoopBuilder loopBuilder = PresetFactory.presetFor("computer-vs-human");
        assertThat(loopBuilder.getFirstTurn(), instanceOf(AsyncComputerTurn.class));
        assertThat(loopBuilder.getSecondTurn(), instanceOf(SinglePlayerHumanTurn.class));
    }

    @Test
    public void itCreatesAHumanVsComputerLoopBuilder() {
        LoopBuilder loopBuilder = PresetFactory.presetFor("human-vs-computer");
        assertThat(loopBuilder.getFirstTurn(), instanceOf(SinglePlayerHumanTurn.class));
        assertThat(loopBuilder.getSecondTurn(), instanceOf(AsyncComputerTurn.class));
    }

    @Test
    public void aComputerVsComputerSettingDefaultsToHumanVsHuman() {
        LoopBuilder loopBuilder = PresetFactory.presetFor("computer-vs-computer");
        assertThat(loopBuilder.getFirstTurn(), instanceOf(MultiPlayerHumanTurn.class));
        assertThat(loopBuilder.getSecondTurn(), instanceOf(MultiPlayerHumanTurn.class));
    }
}
