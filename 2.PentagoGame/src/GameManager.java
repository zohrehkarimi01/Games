import java.util.Scanner;

/**
 * GameManager class implements the whole game
 *
 * @author Zohreh Karimi
 */
public class GameManager {
    private Scanner scan;
    private Player player1;
    private Player player2;
    private Board board;

    /**
     * create a GameManager
     */
    public GameManager() {
        System.out.println("Welcome To Pentago Game:P");
        scan = new Scanner(System.in);
        System.out.println("Player 1 :");
        player1 = new Player(1);
        System.out.println("Player 2 :");
        player2 = new Player(2);
        board = new Board();
    }

    /**
     * Do the move of player in one turn
     *
     * @param playerNum the number of player to do the move
     */
    private void move(int playerNum) {
        board.displayBoard();
        System.out.print("Player " + playerNum + " ");
        if (playerNum == 1) {
            board.printCell(1);
        } else {
            board.printCell(2);
        }
        System.out.println("\b\b:");
        // place marble
        boolean done = false;
        System.out.println("Enter the place of putting marble:");
        do {
            done = board.placeMarble(scan.nextLine(), playerNum);
            if (!done)
                System.out.println("Wrong input!\nEnter again:");
        } while (!done);

        if (board.checkEndgame() != 0 && board.checkEndgame() != 4)
            return;
        board.displayBoard();
        System.out.print("Player " + playerNum + " ");
        if (playerNum == 1) {
            board.printCell(1);
        } else {
            board.printCell(2);
        }
        System.out.println("\b\b:");
        // rotate a block
        System.out.println("Enter the number of Block to rotate:");
        int blockNum = 0, direction = 0;
        do {
            blockNum = scan.nextInt();
            scan.nextLine();
            if (blockNum > 4 || blockNum < 1)
                System.out.println("Wrong input!\nEnter again:");
        } while (blockNum > 4 || blockNum < 1);
        System.out.println("Enter direction of rotation :(1 == clockwise rotation, -1 == counter clockwise rotation)");
        do {
            direction = scan.nextInt();
            scan.nextLine();
            if (direction != 1 && direction != -1)
                System.out.println("Wrong input!\nEnter again:");
        } while (direction != 1 && direction != -1);
        board.rotateBlock(blockNum, direction);
    }

    /**
     * print the result of game
     *
     * @param result the result of game so far
     * @return true if game is ended, Otherwise false
     */
    private boolean printResult(int result) {
        if (result == 0)
            return false;
        board.displayBoard();
        if (result == 1) {
            System.out.print("End Game:\n" + "Winner : Player 1 " );
            board.printCell(1);
            System.out.println("\n" + player1.getNameOfPlayer() + " wins!");
        } else if (result == 2) {
            System.out.print("End Game:\n" + "Winner : Player 2 " );
            board.printCell(2);
            System.out.println("\n" + player2.getNameOfPlayer() + " wins!");
        } else {
            System.out.println("End Game:\n" + "Draw.");
        }
        return true;
    }

    /**
     * Implements game and handles changing turns - the game stars here
     */
    public void startGame() {
        while (true) {
            move(player1.getNumOfPlayer());
            if (printResult(board.checkEndgame()))
                return;
            move(player2.getNumOfPlayer());
            if (printResult(board.checkEndgame()))
                return;
        }
    }
}
