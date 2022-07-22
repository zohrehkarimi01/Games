
/**
 * UNOGame class implements the whole game using gameManager class.
 *
 * @author ZK
 */
public class UNOGame {
    private GameManager gameManager;

    /**
     * create object of UNOGame
     */
    public UNOGame() {
        gameManager = new GameManager();
    }

    /**
     * play UNO game
     *
     * @throws InterruptedException used for waiting 2 seconds after printing the game current situation.
     */
    public void playGame() throws InterruptedException {
        gameManager.startGame();
        while (true) {
            gameManager.play();
            if (gameManager.checkEndGame())
                return;
            gameManager.printGame();
        }
    }
}
