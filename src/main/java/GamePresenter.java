class GamePresenter {
    private StringBuffer output = new StringBuffer();
    private Game game;
    private int id = 1;

    public GamePresenter(Game game) {
        this.game = game;
    }

    public String present() {
        for (Space space : game.getBoard()) {
            addSpace(space);
            addSeparator();

            if (shouldAddHorizontalRule()) addHorizontalRule();
            id++;
        }
        addNewLine();

        return output.toString();
    }

    private void addSpace(Space space) {
        output.append(" ")
                .append(new MarkerPresenter(id, game.getBoard().get(space)))
                .append(" ");
    }

    private void addSeparator() {
        if (isEndOfRow()) {
            addNewLine();
        } else {
            addVerticalSeparator();
        }
    }

    private boolean shouldAddHorizontalRule() {
        return id == 3 || id == 6;
    }

    private void addHorizontalRule() {
        output.append("---+---+---");
        addNewLine();
    }

    private boolean isEndOfRow() {
        return id % 3 == 0;
    }

    private void addNewLine() {
        output.append("\n");
    }

    private void addVerticalSeparator() {
        output.append("|");
    }

    private class MarkerPresenter {
        private Integer id;
        private Marker marker;

        public MarkerPresenter(Integer id, Marker marker) {
            this.id = id;
            this.marker = marker;
        }

        @Override
        public String toString() {
            return (marker != null) ? marker.toString() : id.toString();
        }
    }
}
