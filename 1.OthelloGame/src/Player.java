/**
 * Player class represents a player (human or bot) in the game.
 * It implements player moves and holds it's score.
 * @author Zohreh Karimi
 */
public class Player {

    protected int num;
    protected int numOfOpponent;
    private int score;
    protected int[][] possibleMoves;

    /**
     * create a new player with given info
     * @param num the number of player's turn
     * @param numOfOpponent the number of opponent's turn
     */
    Player(int num, int numOfOpponent){
        this.num = num;
        this.numOfOpponent = numOfOpponent;
        score = 2;
        possibleMoves = new int[8][8];
    }

    // Update the score of player
    // param table the playground table
    private void upDateScore(int[][] table){
        int score = 0;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (table[i][j] == num){
                    score++;
                }
            }
        }
        this.score = score;
    }

    /**
     * Get the score of player which is the number of it's cells
     * @param table the playground table
     * @return the score of player
     */
    public int getScore(int [][] table) {
        upDateScore(table);
        return score;
    }

    /**
     * Get the table that saves possible choices of player
     * @param table the playground of game
     * @return possibleMoves table
     */
    public int[][] getPossibleMoves(int[][] table) {
        checkAllPossibleMoves(table);
        return possibleMoves;
    }

    /**
     * Check all possible choices of player and save them
     * @param table get the playground table
     * @return true if there is at least one possible move for player and false if there is none
     */
    public boolean checkAllPossibleMoves(int[][] table) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                possibleMoves[i][j] = 0;
        }
        boolean flag = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (table[i][j] == 0) {
                    if (i + 1 < 8 && table[i + 1][j] == numOfOpponent) { // 1
                        for (int x = i + 2; x < 8; x++) {
                            if (table[x][j] == 0) {
                                break;
                            } else if (table[x][j] == num) {
                                possibleMoves[i][j] += 1;
                                flag = true;
                                break;
                            }
                        }
                    }
                    if (i - 1 >= 0 && table[i - 1][j] == numOfOpponent) { // 2
                        for (int x = i - 2; x >= 0; x--) {
                            if (table[x][j] == 0) {
                                break;
                            } else if (table[x][j] == num) {
                                possibleMoves[i][j] += 10;
                                flag = true;
                                break;
                            }
                        }
                    }
                    if (j + 1 < 8 && table[i][j+1] == numOfOpponent){ // 3
                        for (int y = j+2; y < 8; y++){
                            if (table[i][y] == 0){
                                break;
                            }else if (table[i][y] == num){
                                possibleMoves[i][j] += 100;
                                flag = true;
                                break;
                            }
                        }
                    }
                    if (j - 1 >= 0 && table[i][j-1] == numOfOpponent){ // 4
                        for (int y = j-2; y >= 0 ; y--){
                            if (table[i][y] == 0){
                                break;
                            }else if (table[i][y] == num){
                                possibleMoves[i][j] += 1000;
                                flag = true;
                                break;
                            }
                        }
                    }
                    if (i - 1 >= 0 && j - 1 >= 0 && table[i-1][j-1] == numOfOpponent){ // 5
                        for (int x = i-2, y = j-2; x >= 0 && y >= 0; x--, y--){
                            if (table[x][y] == 0){
                                break;
                            }else if (table[x][y] == num){
                                possibleMoves[i][j] += 10000;
                                flag = true;
                                break;
                            }
                        }
                    }
                    if (i - 1 >= 0 && j + 1 < 8 && table[i-1][j+1] == numOfOpponent){ // 6
                        for (int x = i-2, y = j+2; x >= 0 && y < 8 ; x--, y++){
                            if (table[x][y] == 0){
                                break;
                            }else if (table[x][y] == num){
                                possibleMoves[i][j] += 100000;
                                flag = true;
                                break;
                            }
                        }
                    }
                    if (i + 1 < 8 && j - 1 >= 0 && table[i+1][j-1] == numOfOpponent){ // 7
                        for (int x = i+2, y = j-2; x < 8 && y >= 0  ; x++, y--){
                            if (table[x][y] == 0){
                                break;
                            }else if (table[x][y] == num){
                                possibleMoves[i][j] += 1000000;
                                flag = true;
                                break;
                            }
                        }
                    }
                    if (i + 1 < 8 && j + 1 < 8 && table[i+1][j+1] == numOfOpponent){ // 8
                        for (int x = i+2, y = j+2; x < 8 && y < 8 ; x++, y++){
                            if (table[x][y] == 0){
                                break;
                            }else if (table[x][y] == num){
                                possibleMoves[i][j] += 10000000;
                                flag = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * Get a choice and implement it if it's possible
     * @param table the playground table
     * @param choice the move that player has chosen
     * @return true if the move has implemented successfully,otherwise false
     */
    public boolean playerMove(int[][] table, String choice) {
        char iChar = choice.charAt(0);
        int i = -1;
        if (Character.isDigit(iChar))
            i = Character.getNumericValue(iChar) - 1;
        char jChar = choice.charAt(2);
        int j = -1;
        if (jChar == 'A')
            j = 0;
        else if (jChar == 'B')
            j = 1;
        else if (jChar == 'C')
            j = 2;
        else if (jChar == 'D')
            j = 3;
        else if (jChar == 'E')
            j = 4;
        else if (jChar == 'F')
            j = 5;
        else if (jChar == 'G')
            j = 6;
        else if (jChar == 'H')
            j = 7;

        if (!(i >= 0 && i < 8 && j >= 0)) {
            return false;
        }

        if (possibleMoves[i][j] > 0){
            table [i][j] = num;
            if (possibleMoves[i][j] % 10 == 1){ // 1
                for (int x = i + 1; x < 8; x++){
                    if (table[x][j] == numOfOpponent)
                        table[x][j] = num;
                    else
                        break;
                }
            }
            possibleMoves[i][j] /= 10;
            if (possibleMoves[i][j] % 10 == 1){ // 2
                for (int x = i-1; x >= 0 ; x--){
                    if (table[x][j] == numOfOpponent)
                        table[x][j] = num;
                    else
                        break;
                }
            }
            possibleMoves[i][j] /= 10;
            if (possibleMoves[i][j] % 10 == 1){ // 3
                for (int y = j+1; y < 8; y++){
                    if (table[i][y] == numOfOpponent)
                        table[i][y] = num;
                    else
                        break;
                }
            }
            possibleMoves[i][j] /= 10;
            if (possibleMoves[i][j] % 10 == 1){ // 4
                for (int y = j-1; y >= 0 ; y--){
                    if (table[i][y] == numOfOpponent)
                        table[i][y] = num;
                    else
                        break;
                }
            }
            possibleMoves[i][j] /= 10;
            if (possibleMoves[i][j] % 10 == 1){ // 5
                for (int x = i-1, y = j-1; x >= 0 && y >= 0; x--, y--){
                    if (table[x][y] == numOfOpponent)
                        table[x][y] = num;
                    else
                        break;
                }
            }
            possibleMoves[i][j] /= 10;
            if (possibleMoves[i][j] % 10 == 1){ // 6
                for (int x = i-1, y = j+1; x >= 0 && y < 8 ; x--, y++){
                    if (table[x][y] == numOfOpponent)
                        table[x][y] = num;
                    else
                        break;
                }
            }
            possibleMoves[i][j] /= 10;
            if (possibleMoves[i][j] % 10 == 1){ // 7
                for (int x = i+1, y = j-1; x < 8 && y >= 0  ; x++, y--){
                    if (table[x][y] == numOfOpponent)
                        table[x][y] = num;
                    else
                        break;
                }
            }
            possibleMoves[i][j] /= 10;
            if (possibleMoves[i][j] % 10 == 1){ // 8
                for (int x = i+1, y = j+1; x < 8 && y < 8 ; x++, y++){
                    if (table[x][y] == numOfOpponent)
                        table[x][y] = num;
                    else
                        break;
                }
            }
            possibleMoves[i][j] /= 10;
            upDateScore(table);
            return true;
        }else {
            return false;
        }
    }
}