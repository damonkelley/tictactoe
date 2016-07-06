package me.damonkelley.io.converters;

import me.damonkelley.tictactoe.Space;

public class SpaceIDConverter {

    private int width;
    private int height;

    public SpaceIDConverter(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Space convert(int spaceId) {
        return new Space(calculateX(spaceId), calculateY(spaceId));
    }

    private int calculateY(int spaceId) {
        return (spaceId - 1) / height;
    }

    private int calculateX(int spaceId) {
        return (spaceId - 1) % width;
    }
}
