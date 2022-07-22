import java.util.ArrayList;

/**
 * Board class represents the game's board
 *
 * @author Zohreh Karimi
 */
public class Board {
    private ArrayList<Block> blocks;

    /**
     * create a new board
     */
    public Board() {
        blocks = new ArrayList<Block>();
        for (int i = 0; i < 4; i++) {
            blocks.add(new Block());
        }
    }

    /**
     * place a marble in the board
     *
     * @param choice    player's choice
     * @param playerNum number of player
     * @return true if placing marble in the board was successful, Otherwise false
     */
    public boolean placeMarble(String choice, int playerNum) {
        if (choice.length() != 2)
            return false;
        char iChar = choice.charAt(1), jChar = choice.charAt(0); // Format of input should be like CI
        int i = -1, j = -1;
        if (Character.isDigit(iChar))
            i = Character.getNumericValue(iChar) - 1;
        if (jChar == 'A' || jChar == 'D')
            j = 0;
        else if (jChar == 'B' || jChar == 'E')
            j = 1;
        else if (jChar == 'C' || jChar == 'F')
            j = 2;
        if (!(i >= 0 && i < 6 && j >= 0))
            return false;
        int blockNum = -1;
        if (i < 3 && (jChar == 'A' || jChar == 'B' || jChar == 'C'))
            blockNum = 0;
        else if (i < 3)
            blockNum = 1;
        else if (jChar == 'A' || jChar == 'B' || jChar == 'C')
            blockNum = 2;
        else
            blockNum = 3;
        i %= 3;
        Block block = blocks.get(blockNum);
        return block.placeMarble(playerNum, i, j);
    }

    // print a cell in the board
    public void printCell(int num) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_BLUE = "\u001B[34m";
        if (num == 0)
            System.out.print("\uD83D\uDF85  ");
        else if (num == 1)
            System.out.print(ANSI_BLUE + "⬤  " + ANSI_RESET);
        else
            System.out.print(ANSI_RED + "⬤  " + ANSI_RESET);
    }

    /**
     * Display the board in console
     */
    public void displayBoard() {
        String reset = "\u001B[0m";
        String fg = "\u001B[97m";
        String bg = "\u001B[107m";

        Block table1 = blocks.get(0);
        Block table2 = blocks.get(1);
        Block table3 = blocks.get(2);
        Block table4 = blocks.get(3);
        int[][] block1 = table1.getBlock();
        int[][] block2 = table2.getBlock();
        int[][] block3 = table3.getBlock();
        int[][] block4 = table4.getBlock();
        System.out.println("  A   B   C     D   E  F");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < 3; j++)
                printCell(block1[i][j]);
            System.out.print("|  ");
            for (int j = 0; j < 3; j++) {
                printCell(block2[i][j]);
                if (j == 2)
                    System.out.println();
            }
        }

        System.out.println("  ----------------------");

        for (int i = 0; i < 3; i++) {
            System.out.print(i + 4 + " ");
            for (int j = 0; j < 3; j++)
                printCell(block3[i][j]);
            System.out.print("|  ");
            for (int j = 0; j < 3; j++) {
                printCell(block4[i][j]);
                if (j == 2)
                    System.out.println();
            }
        }
    }

    /**
     * Check if the game is over or not
     *
     * @return the number of winner if sb wins and 3 if it's a draw and 0 if game is not over
     */
    public int checkEndgame() {
        Block table1 = blocks.get(0);
        Block table2 = blocks.get(1);
        Block table3 = blocks.get(2);
        Block table4 = blocks.get(3);
        int[][] block1 = table1.getBlock();
        int[][] block2 = table2.getBlock();
        int[][] block3 = table3.getBlock();
        int[][] block4 = table4.getBlock();

        boolean player1wins = false;
        boolean player2wins = false;

        for (int num = 1; num < 3; num++) {
            /*
             1 1 1 | 1 1 0
             0 0 0 | 0 0 0
             0 0 0 | 0 0 0
             -------------
             0 0 0 | 0 0 0
             0 0 0 | 0 0 0
             0 0 0 | 0 0 0
             */
            if ((block1[0][0] == num && block1[0][1] == num && block1[0][2] == num && block2[0][0] == num && block2[0][1] == num) ||
                    (block1[1][0] == num && block1[1][1] == num && block1[1][2] == num && block2[1][0] == num && block2[1][1] == num) ||
                    (block1[2][0] == num && block1[2][1] == num && block1[2][2] == num && block2[2][0] == num && block2[2][1] == num) ||

                    (block3[0][0] == num && block3[0][1] == num && block3[0][2] == num && block4[0][0] == num && block4[0][1] == num) ||
                    (block3[1][0] == num && block3[1][1] == num && block3[1][2] == num && block4[1][0] == num && block4[1][1] == num) ||
                    (block3[2][0] == num && block3[2][1] == num && block3[2][2] == num && block4[2][0] == num && block4[2][1] == num) ||
                    /*
                     0 1 1 | 1 1 1
                     0 0 0 | 0 0 0
                     0 0 0 | 0 0 0
                     -------------
                     0 0 0 | 0 0 0
                     0 0 0 | 0 0 0
                     0 0 0 | 0 0 0
                     */
                    (block1[0][1] == num && block1[0][2] == num && block2[0][0] == num && block2[0][1] == num && block2[0][2] == num) ||
                    (block1[1][1] == num && block1[1][2] == num && block2[1][0] == num && block2[1][1] == num && block2[1][2] == num) ||
                    (block1[2][1] == num && block1[2][2] == num && block2[2][0] == num && block2[2][1] == num && block2[2][2] == num) ||

                    (block3[0][1] == num && block3[0][2] == num && block4[0][0] == num && block4[0][1] == num && block4[0][2] == num) ||
                    (block3[1][1] == num && block3[1][2] == num && block4[1][0] == num && block4[1][1] == num && block4[1][2] == num) ||
                    (block3[2][1] == num && block3[2][2] == num && block4[2][0] == num && block4[2][1] == num && block4[2][2] == num) ||
                    /*
                     1 0 0 | 0 0 0
                     1 0 0 | 0 0 0
                     1 0 0 | 0 0 0
                     -------------
                     1 0 0 | 0 0 0
                     1 0 0 | 0 0 0
                     0 0 0 | 0 0 0
                     */
                    (block1[0][0] == num && block1[1][0] == num && block1[2][0] == num && block3[0][0] == num && block3[1][0] == num) ||
                    (block1[0][1] == num && block1[1][1] == num && block1[2][1] == num && block3[0][1] == num && block3[1][1] == num) ||
                    (block1[0][2] == num && block1[1][2] == num && block1[2][2] == num && block3[0][2] == num && block3[1][2] == num) ||

                    (block2[0][0] == num && block2[1][0] == num && block2[2][0] == num && block4[0][0] == num && block4[1][0] == num) ||
                    (block2[0][1] == num && block2[1][1] == num && block2[2][1] == num && block4[0][1] == num && block4[1][1] == num) ||
                    (block2[0][2] == num && block2[1][2] == num && block2[2][2] == num && block4[0][2] == num && block4[1][2] == num) ||
                    /*
                     0 0 0 | 0 0 0
                     1 0 0 | 0 0 0
                     1 0 0 | 0 0 0
                     -------------
                     1 0 0 | 0 0 0
                     1 0 0 | 0 0 0
                     1 0 0 | 0 0 0
                     */
                    (block1[1][0] == num && block1[2][0] == num && block3[0][0] == num && block3[1][0] == num && block3[2][0] == num) ||
                    (block1[1][1] == num && block1[2][1] == num && block3[0][1] == num && block3[1][1] == num && block3[2][1] == num) ||
                    (block1[1][2] == num && block1[2][2] == num && block3[0][2] == num && block3[1][2] == num && block3[2][2] == num) ||

                    (block2[1][0] == num && block2[2][0] == num && block4[0][0] == num && block4[1][0] == num && block4[2][0] == num) ||
                    (block2[1][1] == num && block2[2][1] == num && block4[0][1] == num && block4[1][1] == num && block4[2][1] == num) ||
                    (block2[1][2] == num && block2[2][2] == num && block4[0][2] == num && block4[1][2] == num && block4[2][2] == num) ||
                    /*
                     1 0 0 | 0 0 0
                     0 1 0 | 0 0 0
                     0 0 1 | 0 0 0
                     -------------
                     0 0 0 | 1 0 0
                     0 0 0 | 0 1 0
                     0 0 0 | 0 0 0
                     */
                    (block1[0][0] == num && block1[1][1] == num && block1[2][2] == num && block4[0][0] == num && block4[1][1] == num) ||
                    (block1[1][1] == num && block1[2][2] == num && block4[0][0] == num && block4[1][1] == num && block4[2][2] == num) ||
                    (block1[0][1] == num && block1[1][2] == num && block2[2][0] == num && block4[0][1] == num && block4[1][2] == num) ||
                    (block1[1][0] == num && block1[2][1] == num && block3[0][2] == num && block4[1][0] == num && block4[2][1] == num) ||
                    /*
                     0 0 0 | 0 0 0
                     0 0 0 | 0 1 0
                     0 0 0 | 1 0 0
                     -------------
                     0 0 1 | 0 0 0
                     0 1 0 | 0 0 0
                     1 0 0 | 0 0 0
                     */
                    (block3[2][0] == num && block3[1][1] == num && block3[0][2] == num && block2[2][0] == num && block2[1][1] == num) ||
                    (block3[1][1] == num && block3[0][2] == num && block2[2][0] == num && block2[1][1] == num && block2[0][2] == num) ||
                    (block3[1][0] == num && block3[0][1] == num && block1[2][2] == num && block2[1][0] == num && block2[0][1] == num) ||
                    (block3[2][1] == num && block3[1][2] == num && block4[0][0] == num && block2[2][1] == num && block2[1][2] == num)) {
                if (num == 1)
                    player1wins = true;
                else
                    player2wins = true;
            }
        }
        if (player1wins && player2wins)
            return 3; // draw
        else if (player1wins)
            return 1; // player 1 wins
        else if (player2wins)
            return 2; // player 2 wins
        boolean full = true;
        for (Block block : blocks) {
            int[][] theBlock = block.getBlock();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (theBlock[i][j] == 0)
                        full = false;
                }
            }
        }
        if (full)
            return 4; // draw -- board is full
        else
            return 0; // game is not over yet
    }

    /**
     * rotate a block in the board
     *
     * @param choice    the number of block to be rotated
     * @param direction the direction of rotation
     */
    public void rotateBlock(int choice, int direction) {
        choice--;
        Block selectedBlock = blocks.get(choice);
        selectedBlock.rotate(direction);
    }
}