/**
 * Playground class represents a playground for playing othello.
 * It implements printing table and checking end game.
 * @author Zohreh Karimi
 */
public class Playground {
    private int[][] table;

    /**
     * Create a playground by default
     */
    public Playground() {
        table = new int[8][8];
        startGame();
    }

    /**
     * Get playground table
     * @return table field
     */
    public int[][] getTable() {
        return table;
    }

    // set the table by default to start game
    private void startGame(){
        table[4][3] = 1;
        table[3][4] = 1;
        table[3][3] = 2;
        table[4][4] = 2;
    }

    /**
     * Check end game by checking if possible moves of players
     * @param check1 possible moves of first player
     * @param check2 possible moves of second player
     * @return true if game has reached the end,otherwise false
     */
    public boolean endGame(boolean check1, boolean check2){
        return !(check1 || check2);
    }

    /**
     * print the result of game
     * @param player1 name of player 1
     * @param score1 score of player 1
     * @param player2 name of player 2
     * @param score2 score of player 2
     */
    public void printResult (String player1, int score1, String player2, int score2){
        System.out.println("End Game:");
        System.out.println("Player 1: " + player1 + "\nScore: " + score1);
        System.out.println("Player 2: " + player2 + "\nScore: " + score2);
        if (score1 > score2)
            System.out.println(player1 + " wins!");
        else if (score2 > score1)
            System.out.println(player2 + " wins!");
        else
            System.out.println("Draw.");
    }

    /**
     * print the table of game
     * @param possibleMoves get the possible moves of player that is her/his turn
     */
    public void printTable(int [][] possibleMoves){
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BRIGHT_WHITE  = "\u001B[97m";
        String ANSI_BRIGHT_BG_WHITE  = "\u001B[107m";
        String ANSI_WHITE  = "\u001B[37m";

        System.out.print("  A");
        System.out.print(ANSI_BRIGHT_WHITE + ANSI_BRIGHT_BG_WHITE + "⬤");
        System.out.print(ANSI_RESET + "B");
        System.out.print(ANSI_BRIGHT_WHITE + ANSI_BRIGHT_BG_WHITE + "⬤");
        System.out.print(ANSI_RESET + "C");
        System.out.print(ANSI_BRIGHT_WHITE + ANSI_BRIGHT_BG_WHITE + "⬤");
        System.out.print(ANSI_RESET + "D");
        System.out.print(ANSI_BRIGHT_WHITE + ANSI_BRIGHT_BG_WHITE + "⬤");
        System.out.print(ANSI_RESET + "E");
        System.out.print(ANSI_BRIGHT_WHITE + ANSI_BRIGHT_BG_WHITE + "⬤");
        System.out.print(ANSI_RESET + "F");
        System.out.print(ANSI_BRIGHT_WHITE + ANSI_BRIGHT_BG_WHITE + "⬤");
        System.out.print(ANSI_RESET + "G");
        System.out.print(ANSI_BRIGHT_WHITE + ANSI_BRIGHT_BG_WHITE + "⬤");
        System.out.print(ANSI_RESET + "H");
        System.out.println();
        for (int i = 0; i < 8; i++){
            System.out.print((i+1) + "|");
            for (int j = 0; j < 8; j++){
                if (table[i][j] == 1){
                    System.out.print("⬤|");
                }else if (table[i][j] == 2){
                    System.out.print("\uD83D\uDF85|");

                }else {
                    if (possibleMoves[i][j] > 0) {
                        System.out.print(ANSI_WHITE + ANSI_WHITE + "\u25CD" + ANSI_RESET + "|");
                    }else {
                        System.out.print(ANSI_BRIGHT_WHITE + ANSI_BRIGHT_BG_WHITE +"⬤"+ ANSI_RESET + "|");
                    }
                }
                if (j == 7)
                    System.out.println();
            }
        }
    }
}