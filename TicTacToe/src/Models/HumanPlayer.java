package Models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Scanner;
@SuperBuilder
@Getter
@Setter
public class HumanPlayer extends Player{
    User user;
    @Override
    Move play(Board board) {
        Scanner in = new Scanner(System.in);

        System.out.println("Please tell the row where you want to make the move, " +
                "starting from 0");
        int row = in.nextInt();
        System.out.println("Please tell the col where you want to make the move, " +
                "starting from 0");
        int col = in.nextInt();
        Cell cell = new Cell(row, col);
        Move move = new Move(cell, this);
        return move;
    }
}
