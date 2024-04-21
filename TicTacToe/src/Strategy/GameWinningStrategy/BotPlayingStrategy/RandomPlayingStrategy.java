package Strategy.GameWinningStrategy.BotPlayingStrategy;

import Models.Board;
import Models.Cell;
import Models.CellState;
import Models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPlayingStrategy implements BotPlayingStrategy {


    private List<Cell> getEmptyCells(Board board)
    {
        List<Cell> emptyCells = new ArrayList<>();
        for(int i = 0; i < board.getBoard().size(); i++)
        {
            for(int j = 0; j < board.getBoard().size(); j++) {
                if(board.getBoard().get(i).get(j).getCellState() == CellState.EMPTY)
                {
                    emptyCells.add(board.getBoard().get(i).get(j));
                }
            }
        }
        return emptyCells;
    }
    @Override
    public Cell play(Board board) {
        List<Cell> emptyCells = getEmptyCells(board);
        Random random = new Random();
        int randomIndex = random.nextInt(emptyCells.size());
        return emptyCells.get(randomIndex);
    }
}
