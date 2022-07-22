/**
 *  BotPlayer class represents a bot for playing othello
 *  It inherits from the Player class
 * @author Zohreh Karimi
 */
public class BotPlayer extends Player {

    /**
     * create a bot player
     */
    public BotPlayer(){
        super(2,1);
    }

    /**
     * Choose a move as a bot
     * @param table the playground of game
     * @return the choice of botPlayer
     */
    public String createMove(int[][] table) {
        checkAllPossibleMoves(table);
        int maxScore = 0, xChoice = 0, yChoice = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0 && j == 0 && possibleMoves[i][j] > 0)
                    return "1 A";
                if (i == 0 && j == 7 && possibleMoves[i][j] > 0)
                    return "1 H";
                if (i == 7 && j == 0 && possibleMoves[i][j] > 0)
                    return "8 A";
                if (i == 7 && j == 7 && possibleMoves[i][j] > 0)
                    return "8 H";
                int tmp = 0;
                int moves = possibleMoves[i][j];
                if (moves > 0){
                    if (moves % 10 == 1){ // 1
                        for (int x = i + 1; x < 8; x++){
                            if (table[x][j] == numOfOpponent)
                                tmp++;
                            else
                                break;
                        }
                    }
                    moves /= 10;
                    if (moves % 10 == 1){ // 2
                        for (int x = i-1; x >= 0 ; x--){
                            if (table[x][j] == numOfOpponent)
                                tmp++;
                            else
                                break;
                        }
                    }
                    moves /= 10;
                    if (moves % 10 == 1){ // 3
                        for (int y = j+1; y < 8; y++){
                            if (table[i][y] == numOfOpponent)
                                tmp++;
                            else
                                break;
                        }
                    }
                    moves /= 10;
                    if (moves % 10 == 1){ // 4
                        for (int y = j-1; y >= 0 ; y--){
                            if (table[i][y] == numOfOpponent)
                                tmp++;
                            else
                                break;
                        }
                    }
                    moves /= 10;
                    if (moves % 10 == 1){ // 5
                        for (int x = i-1, y = j-1; x >= 0 && y >= 0; x--, y--){
                            if (table[x][y] == numOfOpponent)
                                tmp++;
                            else
                                break;
                        }
                    }
                    moves /= 10;
                    if (moves % 10 == 1){ // 6
                        for (int x = i-1, y = j+1; x >= 0 && y < 8 ; x--, y++){
                            if (table[x][y] == numOfOpponent)
                                tmp++;
                            else
                                break;
                        }
                    }
                    moves /= 10;
                    if (moves % 10 == 1){ // 7
                        for (int x = i+1, y = j-1; x < 8 && y >= 0  ; x++, y--){
                            if (table[x][y] == numOfOpponent)
                                tmp++;
                            else
                                break;
                        }
                    }
                    moves /= 10;
                    if (moves % 10 == 1){ // 8
                        for (int x = i+1, y = j+1; x < 8 && y < 8 ; x++, y++){
                            if (table[x][y] == numOfOpponent)
                                tmp++;
                            else
                                break;
                        }
                    }
                    if (tmp >= maxScore){
                        maxScore = tmp;
                        xChoice = i;
                        yChoice = j;
                    }
                }
            }
        }
        String move = "" + (xChoice + 1);
        switch (yChoice){
            case(0):
                move = move + " A";
                break;
            case(1):
                move = move + " B";
                break;
            case(2):
                move = move + " C";
                break;
            case(3):
                move = move + " D";
                break;
            case(4):
                move = move + " E";
                break;
            case(5):
                move = move + " F";
                break;
            case(6):
                move = move + " G";
                break;
            case(7):
                move = move + " H";
                break;
        }
        return move;
    }

    /**
     * bot plays and places a marble if it's possible
     * @param table the playground of game
     */
    public void play(int[][] table){
        boolean possible = checkAllPossibleMoves(table);
        if (possible){ // place a marble
            String choice = createMove(table);
            System.out.println(choice);
            playerMove(table, choice);
        }else{
            System.out.println("pass."); // pass to the next turn
        }
    }
}