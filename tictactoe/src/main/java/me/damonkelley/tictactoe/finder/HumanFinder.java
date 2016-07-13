package me.damonkelley.tictactoe.finder;

import me.damonkelley.io.converters.SpaceIDConverter;
import me.damonkelley.io.validators.SpaceIDInputValidator;
import me.damonkelley.tictactoe.Game;
import me.damonkelley.tictactoe.Space;
import me.damonkelley.ui.UI;

public class HumanFinder extends Finder {
    private UI ui;
    private int size;

    public HumanFinder(UI ui) {
        this.ui = ui;
        this.size = 3;
    }

    @Override
    public Space getNextMove(Game game) {
        size = game.getBoard().getSize();
        return new SpaceIDConverter(size, size).convert(getParsedUserInput());
    }

    private int getParsedUserInput() {
        return parse(getUserInput());
    }

    private String getUserInput() {
        return ui.prompt("Space #: ", new SpaceIDInputValidator(1, size*size));
    }

    private int parse(String input) {
        return Integer.parseInt(input);
    }
}
