package Strategy.GameWinningStrategy;

import Models.Board;
import Models.Move;
import Models.Player;

public interface GameWinningStrategy {
     Boolean checkWinner(Board board, Move move, Player player);
}
