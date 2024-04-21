package Models;

import Strategy.GameWinningStrategy.BotPlayingStrategy.BotPlayingStrategy;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class BotPlayer extends Player{

    Level DifficultyLevel;
    BotPlayingStrategy botPlayingStrategy;

    @Override
    Move play(Board board) {
        Cell cell = botPlayingStrategy.play(board);
        cell.setPlayer(this);
        return new Move(cell, this);
    }
}
