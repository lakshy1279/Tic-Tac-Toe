package Strategy.GameWinningStrategy.BotPlayingStrategy;

import Models.Board;
import Models.Cell;
import Models.Player;

public interface BotPlayingStrategy {
    Cell play(Board board);
}
