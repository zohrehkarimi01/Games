import java.util.Scanner;
/**
 * GameManager class implements the othello game using HumanPlayer and BotPlayer and Playground classes
 * It implements both playing with computer and 2-player game
 * @author Zohreh Karimi
 */
public class GameManager {

    private Scanner scan;
    private int typeOfGame;
    private Playground playground;
    private HumanPlayer player1;
    private HumanPlayer player2;
    private BotPlayer bot;

    /**
     * create a game manager for othello game
     */
    public GameManager(){
        scan = new Scanner(System.in);
        System.out.println("Welcome to Othello Game:)\nChoose the type of game:\n1)one player(play with computer)\n2)two players");
        do {
            typeOfGame = scan.nextInt();
            scan.nextLine();
        }while (typeOfGame != 1 && typeOfGame != 2);
        // Create playground
        playground = new Playground();
        // create 2 players with given names or a bot and a human player
        if (typeOfGame == 1){
            player1 = new HumanPlayer(1, 2);
            bot = new BotPlayer();
        }else {
            System.out.println("Player 1:");
            player1 = new HumanPlayer(1, 2);
            System.out.println("Player 2:");
            player2 = new HumanPlayer(2, 1);
        }
    }

    /**
     * start method implements the othello game playing in two ways for playing with computer or 2-player game
     */
    public void start(){
        while (true){
            // player number one plays
            playground.printTable(player1.getPossibleMoves(playground.getTable()));
            System.out.println("Player1 \u2B24:");
            player1.play(playground.getTable());
            // check end game
            if (checkEndGame()){
                return;
            }
            // check type of game
            if (typeOfGame == 1) { // bot plays
                playground.printTable(bot.getPossibleMoves(playground.getTable()));
                System.out.println("Player2 \uD83D\uDF85:");
                bot.play(playground.getTable());
            } else { // player number 2 plays
                playground.printTable(player2.getPossibleMoves(playground.getTable()));
                System.out.println("Player2 \uD83D\uDF85:");
                player2.play(playground.getTable());
            }
            // check end game
            if (checkEndGame()){
                return;
            }
        }
    }

    /**
     * checks the game is over or not.if it is over, print results
     * @return true if game is over,Otherwise false
     */
    public boolean checkEndGame(){
        if (typeOfGame == 1){
            if (playground.endGame(player1.checkAllPossibleMoves(playground.getTable()), bot.checkAllPossibleMoves(playground.getTable()))){
                playground.printTable(player1.getPossibleMoves(playground.getTable()));
                playground.printResult(player1.getPlayerName(), player1.getScore(playground.getTable()), "Bot", bot.getScore(playground.getTable()));
                return true;
            }
        }else{
            if (playground.endGame(player1.checkAllPossibleMoves(playground.getTable()), player2.checkAllPossibleMoves(playground.getTable()))){
                playground.printTable(player1.getPossibleMoves(playground.getTable()));
                playground.printResult(player1.getPlayerName(), player1.getScore(playground.getTable()), player2.getPlayerName(), player2.getScore(playground.getTable()));
                return true;
            }
        }
        return false;
    }
}