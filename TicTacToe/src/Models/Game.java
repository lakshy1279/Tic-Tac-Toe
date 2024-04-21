package Models;

import Strategy.GameWinningStrategy.GameWinningStrategy;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Game {
    private Board board;
    private List<Player> players;

    private int nextPlayerIndex = 0;

    private GameWinningStrategy gameWinningStrategy;
    private GameStatus gameStatus;
    private  Player winner;
    private List<Move> moves;
    
    public Boolean checkMove(Move move)
    {
        int boardSize = this.board.getBoard().size();
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        return row >= 0 && col >= 0 && row < boardSize && col < boardSize && move.getCell().cellState == CellState.EMPTY;
    }

    public void makeMove()
    {
         Player toMovePlayer = this.players.get(nextPlayerIndex);
         Move move = toMovePlayer.play(this.board);
         while (!checkMove(move))
         {
             System.out.println("Wrong Move Please try again");
             move = toMovePlayer.play(this.board);
         }
         int row = move.getCell().getRow();
         int col = move.getCell().getCol();
         System.out.println("Move Happend at" + " " + row + " " + col);
         this.board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
         this.board.getBoard().get(row).get(col).setPlayer(toMovePlayer);
         moves.add(move);
         if(gameWinningStrategy.checkWinner(this.board, move, toMovePlayer))
         {
             this.gameStatus = GameStatus.ENDED;
             this.winner = toMovePlayer;
         }
         if(moves.size() == this.board.getBoard().size() * this.board.getBoard().size())
         {
             this.gameStatus = GameStatus.DRAW;
         }
         nextPlayerIndex+=1;
         nextPlayerIndex = nextPlayerIndex % players.size();
    }

    public void undo()
    {
        if(moves.size() == 0)
        {
            System.out.println("There is no move to undo");
            makeMove();
            return;
        }
        Move move = this.moves.get(moves.size()-1);
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        this.board.getBoard().get(row).get(col).setCellState(CellState.EMPTY);
        this.board.getBoard().get(row).get(col).setPlayer(null);
        this.moves.remove(move);
        nextPlayerIndex -=1;
    }

    public static Builder getBuilder(){
        return new Builder();
    }
    public static class Builder{
        private int dimension;
        private List<Player> players;
        private GameWinningStrategy gameWinningStrategy;
        public Builder setDimension(int dimension){
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players){
            this.players = players;
            return this;
        }

        public Builder setGameWinningStrategy(GameWinningStrategy gameWinningStrategy){
            this.gameWinningStrategy = gameWinningStrategy;
            return this;
        }

        private void  validate() throws IllegalArgumentException{
            if(dimension < 3){
              throw new IllegalArgumentException("Dimension should be at least 3");
            }

            if(this.players.size() != this.dimension-1){
              throw new IllegalArgumentException("Number of players should be 1 less than the dimension");
            }

            Set<Character> str = new HashSet<>();
            int botCounter = 0;
            for(Player p : this.players){
                if(str.contains(p.getSymbol()))
                {
                    throw new IllegalArgumentException("Player " + p.getSymbol() + " already exists");
                }
                if(p instanceof BotPlayer){
                    botCounter++;
                    if(botCounter >  1){
                        throw new IllegalArgumentException("Game can't have more than one bot");
                    }
                }
                str.add(p.getSymbol());
            }
        }

        public Game build() throws IllegalArgumentException{
            Game game = new Game();
            game.setBoard(new Board(this.dimension));
            game.setPlayers(this.players);
            game.setGameWinningStrategy(this.gameWinningStrategy);
            game.setGameStatus(GameStatus.INPROGRESS);
            game.setMoves(new ArrayList<Move>());
            return game;
        }
    }
}
