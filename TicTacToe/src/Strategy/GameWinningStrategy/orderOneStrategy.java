package Strategy.GameWinningStrategy;

import Models.*;


public class orderOneStrategy implements GameWinningStrategy {
    @Override
    public Boolean checkWinner(Board board, Move move, Player player) {
        // check column
        char playerSymbol = player.getSymbol();
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        boolean colCheck = true, rowCheck = true, rightdiagonalCheck = true, leftdiagonalCheck = true;
         for(int j = 0; j < board.getBoard().size(); j++)
         {
             if(board.getBoard().get(row).get(j).getCellState() == CellState.EMPTY || board.getBoard().get(row).get(j).getPlayer().getSymbol() != playerSymbol) {
                 colCheck = false;
                 break;
             }
         }
        //check row
        for(int j = 0; j < board.getBoard().size(); j++)
        {
            if(board.getBoard().get(j).get(col).getCellState() == CellState.EMPTY || board.getBoard().get(j).get(col).getPlayer().getSymbol() != playerSymbol)
            {
               rowCheck = false;
               break;
            }
        }
        // check right diagonal
        for(int i = 0; i < board.getBoard().size(); i++)
        {
            if(board.getBoard().get(i).get(i).getCellState() == CellState.EMPTY || board.getBoard().get(i).get(i).getPlayer().getSymbol() != playerSymbol)
            {
                rightdiagonalCheck = false;
                break;
            }
        }

        // check left diagonal
        int i = 0;
        int j = board.getBoard().size() - 1;
        while(i < board.getBoard().size() && j >= 0)
        {
            if( board.getBoard().get(i).get(j).getCellState() == CellState.EMPTY || board.getBoard().get(i).get(j).getPlayer().getSymbol() != playerSymbol )
            {
               leftdiagonalCheck = false;
               break;
            }
            i++;
            j--;
        }
        return colCheck || rowCheck || rightdiagonalCheck || leftdiagonalCheck;
    }
}
