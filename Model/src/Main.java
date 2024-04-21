import Models.*;
import Strategy.GameWinningStrategy.BotPlayingStrategy.BotPlayingStrategy;
import Strategy.GameWinningStrategy.BotPlayingStrategy.RandomPlayingStrategy;
import Strategy.GameWinningStrategy.GameWinningStrategy;
import Strategy.GameWinningStrategy.orderOneStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        System.out.println("Enter the Dimension of the board");
        Scanner in = new Scanner(System.in);
        int dimension = in.nextInt();
        List<Player> players = new ArrayList<>();
        int playersCount = dimension - 1;
        System.out.println("Is there any bot Player?");
        String isBot = in.next();
        if(isBot.equals("y"))
        {
           playersCount--;
           BotPlayingStrategy botPlayingStrategy = new RandomPlayingStrategy();
            System.out.println("Enter the symbol of the bot: ");
            char symbol = in.next().charAt(0);
           Player player = BotPlayer.builder().symbol(symbol).DifficultyLevel(Level.EASY).botPlayingStrategy(botPlayingStrategy).build();
           players.add(player);
        }
        for(int i = 0; i < playersCount; i++)
        {
            System.out.println("Enter the symbol of the Player: ");
            char symbol = in.next().charAt(0);
            Player player = HumanPlayer.builder().user(new User("test", "a@gmail.com")).symbol(symbol).build();
            players.add(player);
        }
        GameWinningStrategy gameWinningStrategy = new orderOneStrategy();
        Game game = new Game.Builder().setDimension(dimension).setGameWinningStrategy(gameWinningStrategy).setPlayers(players).build();
        while (game.getGameStatus().equals(GameStatus.INPROGRESS))
        {
            System.out.println("This is current state of the board:");
            game.getBoard().printBoard();
            System.out.println("Want to undo last move? (y/n)");
            String isUndo = in.next();
            if(isUndo.equals("y"))
            {
                game.undo();
            }
            else
                game.makeMove();
        }

        System.out.println("Game has ended. The result was: ");
        if(game.getGameStatus().equals(GameStatus.ENDED)){
            System.out.println("The winner is: " + game.getWinner().getSymbol());
            game.getBoard().printBoard();
        }
        else
        {
            System.out.println("Draw");
            game.getBoard().printBoard();
        }
    }
}