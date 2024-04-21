package Models;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class Player {
    char symbol;

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    abstract Move play(Board board);
}
