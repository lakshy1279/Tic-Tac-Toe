package Models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public Board(int size) {
        this.size = size;
        this.board = new ArrayList<>();
        for(int i = 0; i < size; i++)
            this.board.add(new ArrayList<>());
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
               this.board.get(i).add(new Cell(i, j));
            }
        }
    }

    private List<List<Cell>> board;

    int size;

    public List<List<Cell>> getBoard() {
        return board;
    }

    public  void printBoard() {
        for(int i = 0; i < board.size(); i++)
        {
            for(int j = 0; j < board.size(); j++)
            {
                if(board.get(i).get(j).getCellState() == CellState.EMPTY)
                    System.out.print("|" + "  " + "|" + " ");
                else
                    System.out.print("|" + board.get(i).get(j).getPlayer().getSymbol() + "|" + " ");
            }
            System.out.println();
        }
    }
}
