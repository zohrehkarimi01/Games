import java.util.Scanner;

/**
 * Player class represents a player in Pentago game
 *
 * @author Zohreh Karimi
 */
public class Player {
    private String nameOfPlayer;
    private int numOfPlayer;

    /**
     * create a player object
     *
     * @param num number of player
     */
    public Player(int num) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your name:");
        nameOfPlayer = scan.nextLine();
        numOfPlayer = num;
    }

    /**
     * get name of player
     *
     * @return player's name
     */
    public String getNameOfPlayer() {
        return nameOfPlayer;
    }

    /**
     * get number of player
     *
     * @return player's number
     */
    public int getNumOfPlayer() {
        return numOfPlayer;
    }
}
