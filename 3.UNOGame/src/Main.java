/**
 * Main class - program starts here
 *
 * @author ZK
 */
public class Main {

    /**
     * the starting point of program
     *
     * @param args not used
     * @throws InterruptedException used for waiting 2 seconds after printing the game current situation.
     */
    public static void main(String[] args) throws InterruptedException {
        UNOGame unoGame = new UNOGame();
        unoGame.playGame();
    }
}
