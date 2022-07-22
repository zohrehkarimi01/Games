/**
 * Block class represents a block in game board
 *
 * @author Zohreh Karimi
 */
public class Block {
    private int[][] block;

    /**
     * create a block of board
     */
    public Block() {
        block = new int[3][3];
    }

    /**
     * get the block
     *
     * @return block field
     */
    public int[][] getBlock() {
        return block;
    }

    /**
     * Implementing placing a marble in the board block
     *
     * @param playerNum number of player
     * @param i         the row of chosen place
     * @param j         the column of chosen place
     * @return true if placing marble was successful, Otherwise false
     */
    public boolean placeMarble(int playerNum, int i, int j) {
        if (block[i][j] > 0)
            return false;
        else {
            block[i][j] = playerNum;
            return true;
        }
    }

    /**
     * Rotate the block clockwise or counter clockwise
     *
     * @param direction direction of rotation
     */
    public void rotate(int direction) {
        int[][] rotatedBlock = new int[3][3];
        if (direction == 1) { // clockwise rotation
            for (int j = 2, x = 0; j >= 0; j--, x++) {
                for (int i = 0, y = 0; i < 3; i++, y++)
                    rotatedBlock[i][j] = block[x][y];
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++)
                    block[i][j] = rotatedBlock[i][j];
            }
        } else { // counter clockwise rotation
            for (int j = 0, x = 0; j < 3; j++, x++) {
                for (int i = 2, y = 0; i >= 0; i--, y++)
                    rotatedBlock[i][j] = block[x][y];
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++)
                    block[i][j] = rotatedBlock[i][j];
            }
        }
    }
}
