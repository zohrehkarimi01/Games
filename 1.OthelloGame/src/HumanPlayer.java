import java.util.Scanner;

/**
 * HumanPlayer class represents a human player in the game
 * It inherits from the Player class
 * @author Zohreh Karimi
 */
public class HumanPlayer extends Player{

    private String playerName;
    private Scanner scan;

    /**
     * create a human player with given name
     * @param num the number of player's turn
     * @param numOfOpponent the number of opponent's turn
     */
    public HumanPlayer(int num , int numOfOpponent){
        super(num, numOfOpponent);
        scan = new Scanner(System.in);
        System.out.println("Enter your name:");
        this.playerName = scan.nextLine();
    }

    /**
     * Get player's name
     * @return the name of player
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * user plays and places a marble if it's possible
     * @param table the playground of game
     */
    public void play(int[][] table){
        // check if there is any possible choice for player or not
        boolean possible = checkAllPossibleMoves(table);
        if (possible){ // do the choice if possible
            System.out.println("Enter your choice:");
            boolean flag = false;
            do {
                String choice = scan.nextLine();
                if (choice.length() == 3)
                    flag = playerMove(table, choice);
                if (!flag)
                    System.out.println("Wrong input!\nEnter again:");
            }while (!flag);
        }else{ // pass to the next turn
            System.out.println("pass.");
        }
    }
}