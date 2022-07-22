import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * GameManager class implements playing UNO
 * It holds cards repository and players and the methods for playing game
 *
 * @author ZK
 */
public class GameManager {
    // cards repository
    private ArrayList<Card> cards;
    // player of game
    private ArrayList<Player> players;
    // number of players
    private int numOfPlayers;
    // the card in the middle
    private Card middleCard;
    // the current color of playing
    private String gameColor;
    // direction of rotation
    private int direction;
    // managing skip situation
    private int skip;
    // managing draw situation
    private int drawSituation;
    // the play turn number
    private int playerTurn;
    // single or multi player type
    private int typeOfPlay;

    private Random random;
    private Scanner scan;

    /**
     * create a game manager for playing UNO
     */
    public GameManager() {
        random = new Random();
        scan = new Scanner(System.in);
        cards = new ArrayList<Card>();
        initializeCards();
        players = new ArrayList<Player>();
        direction = -1;  // -1 is clockwise and 1 is counterclockwise
        drawSituation = 0;
        skip = 0;
        typeOfPlay = -1;
    }

    /*
     * create 108 cards of the game and initialize them
     */
    private void initializeCards() {
        String[] colors = {"Red", "Yellow", "Green", "Blue"};
        for (String color : colors) {
            cards.add(new WildColorCard());
            cards.add(new DrawCard(color));
            cards.add(new ReverseCard(color));
            cards.add(new SkipCard(color));
            for (int i = 0; i < 10; i++) {
                cards.add(new NumericCard(color, i));
            }
        }
        for (String color : colors) {
            cards.add(new WildDrawCard());
            cards.add(new DrawCard(color));
            cards.add(new ReverseCard(color));
            cards.add(new SkipCard(color));
            for (int i = 1; i < 10; i++) {
                cards.add(new NumericCard(color, i));
            }
        }
    }

    /**
     * check if the game is over or not by finishing 1 player's card.
     *
     * @return true if the game has finished, otherwise false.
     */
    public boolean checkEndGame() {
        for (Player player : players) {
            if (player.getNumOfCards() == 0) {
                printResults();
                return true;
            }

        }
        return false;
    }

    /*
     * print the result of game.
     */
    private void printResults() {
        // sorting players based on their scores
        Player[] players1 = new Player[numOfPlayers];
        for (int i = 0; i < players.size(); i++) {
            players1[i] = players.get(i);
        }
        Player temp;
        for (int i = 0; i < numOfPlayers - 1; i++) {
            for (int j = 0; j < numOfPlayers - i - 1; j++) {
                if (players1[j].getScore() > players1[j + 1].getScore()) {
                    temp = players1[j];
                    players1[j] = players1[j + 1];
                    players1[j + 1] = temp;
                }
            }
        }
        // printing players scores and announce the winner
        System.out.println();
        System.out.println("\u001B[34m" + "\u2A37 End Game \u2A37" + "\u001B[0m" + "\nPlayers scores: ");
        for (Player player : players1) {
            System.out.print("Player " + player.getNumberOfPlayer());
            if (player instanceof HumanPlayer)
                System.out.print("(" + ((HumanPlayer) player).getName() + ")");
            System.out.println(": " + player.getScore() + " scores.");
        }
        System.out.print("\u001B[31m" + "Winner is Player " + players1[0].getNumberOfPlayer() + (players1[0] instanceof HumanPlayer ? " " +
                ((HumanPlayer) players1[0]).getName() + " " : " ") + "!!!" + "\u001B[0m");
    }

    /*
     * distributing (dealing) the cards among players in the beginning of the game
     */
    private void dealingCards() {
        for (int i = 1; i <= 7; i++) {
            for (Player player : players) {
                int index = random.nextInt(cards.size());
                player.AddCard(cards.get(index));
                cards.remove(index);
            }
        }
    }

    /**
     * Start the game (choosing a random player for beginner, choosing a random card for starting the game ...)
     *
     * @throws InterruptedException used for waiting 2 seconds after printing the game current situation.
     */
    public void startGame() throws InterruptedException {
        System.out.println("Welcome to UNO game:)\nEnter the number of players(3,4 or 5 players):");
        do {
            numOfPlayers = scan.nextInt();
            scan.nextLine();
            if (numOfPlayers > 5 || numOfPlayers < 3)
                System.out.println("Invalid input!\nEnter 3,4 or 5 for the number of players:");
        } while (numOfPlayers > 5 || numOfPlayers < 3);
        System.out.println("Choose Type of play: \n1) single player\n2) multi player ");
        do {
            typeOfPlay = scan.nextInt();
            scan.nextLine();
            if (typeOfPlay != 1 && typeOfPlay != 2)
                System.out.println("Invalid input!\nEnter again:");
        } while (typeOfPlay != 1 && typeOfPlay != 2);
        // creating players
        if (typeOfPlay == 1) {
            players.add(new HumanPlayer(1));
            for (int i = 2; i <= numOfPlayers; i++) {
                players.add(new ComputerPlayer(i));
            }
        } else {
            for (int i = 1; i <= numOfPlayers; i++) {
                System.out.println("Player " + i + ":");
                players.add(new HumanPlayer(i));
            }
        }

        dealingCards(); // distributing (dealing) cards among players
        playerTurn = random.nextInt(numOfPlayers); // choosing first player
        // picking the first card to begin the game
        do {
            middleCard = cards.get(random.nextInt(cards.size()));
        } while (middleCard.getColor().equals("Black"));
        cards.remove(middleCard);
        gameColor = middleCard.getColor();
        printGame();
        if (middleCard.getType().equals("Draw")) {
            drawSituation = 2;
        } else if (middleCard.getType().equals("Reverse")) {
            direction = 1;
        } else if (middleCard.getType().equals("Skip"))
            skip = 1;
    }

    /**
     * Print the game current situation.
     *
     * @throws InterruptedException used for waiting 2 seconds after printing.
     */
    public void printGame() throws InterruptedException {

        String ANSI_RESET = "\u001B[0m";
        String currentColor = middleCard.getColorToPrint();


        System.out.println();
        System.out.println("          << color : " + gameColor + "    Rotation : " + (direction == 1 ? "counter clockwise" : "clockwise") + " >>");
        System.out.println();

        System.out.println("                    " + currentColor + "                     " + ANSI_RESET);
        System.out.println("                    " + currentColor + "                     " + ANSI_RESET);
        System.out.println("                    " + middleCard.getNameToPrint());
        System.out.println("                    " + currentColor + "                     " + ANSI_RESET);
        System.out.println("                    " + currentColor + "                     " + ANSI_RESET);
        System.out.println();

        for (int i = 0; i < numOfPlayers; i++) {
            System.out.println("  Player " + players.get(i).getNumberOfPlayer() +
                    (players.get(i) instanceof HumanPlayer ? " " + ((HumanPlayer) players.get(i)).getName() : " ") +
                    " : " + players.get(i).getNumOfCards() + " cards      ");

            if (players.get(i) instanceof HumanPlayer) {
                if (typeOfPlay == 1) {
                    System.out.println("  Your cards:");
                    ((HumanPlayer) players.get(i)).printAllCards();
                } else {
                    if (i == playerTurn)
                        ((HumanPlayer) players.get(i)).printAllCards();
                }
            }
        }
        System.out.println();
        System.out.println("                  " + "-->> Player " + (playerTurn + 1) + "'s turn <<--");
        System.out.println();
        Thread.sleep(2000);
    }

    /*
     * add some random cards to player's cards.
     *
     * @param player     the player which we want to add cards to it's cards.
     * @param numOfCards number of cards to be added.
     */
    private void addCards(Player player, int numOfCards) {
        for (int i = 1; i <= numOfCards; i++) {
            int index = random.nextInt(cards.size());
            player.AddCard(cards.get(index));
            cards.remove(index);
        }
    }


    /**
     * one player is playing in its turn
     *
     * @throws InterruptedException used for waiting 2 seconds after printing the game current situation.
     */
    public void play() throws InterruptedException {
        if (middleCard.getType().equals("Skip") && skip == 1) {
            System.out.println("Skip.");
            skip = 0;
            changeTurn();
            return;
        } else if (middleCard.getType().equals("Draw") && drawSituation != 0 && !(players.get(playerTurn).containsDraw("Draw"))) {
            addCards(players.get(playerTurn), drawSituation);
            System.out.println("Draw.");
            drawSituation = 0;
            changeTurn();
            return;
        } else if (middleCard.getType().equals("WildDraw") && drawSituation != 0 && !(players.get(playerTurn).containsDraw("WildDraw"))) {
            addCards(players.get(playerTurn), drawSituation);
            System.out.println("WildDraw.");
            drawSituation = 0;
            changeTurn();
            return;
        }
        if (players.get(playerTurn).possibleAction(middleCard, gameColor)) {
            Card returnCard = middleCard;
            middleCard = players.get(playerTurn).play(middleCard, gameColor, drawSituation);
            cards.add(returnCard);
        } else {
            if (players.get(playerTurn) instanceof HumanPlayer) {
                int input = -1;
                System.out.println("You have no possible choice in your cards.To pick a random card from repository enter 0:");
                do {
                    input = scan.nextInt();
                    scan.nextLine();
                    if (input != 0)
                        System.out.println("Invalid input!\nEnter again:");
                } while (input != 0);
            }
            addCards(players.get(playerTurn), 1);
            if (players.get(playerTurn).possibleAction(middleCard, gameColor) && (players.get(playerTurn) instanceof HumanPlayer))
                printGame();
            if (players.get(playerTurn).possibleAction(middleCard, gameColor)) {
                Card returnCard = middleCard;
                middleCard = players.get(playerTurn).play(middleCard, gameColor, drawSituation);
                cards.add(returnCard);
            } else {
                changeTurn();
                return;
            }
        }
        if (middleCard.getType().equals("Draw")) {
            drawSituation += 2;
        } else if (middleCard.getType().equals("Reverse")) {
            direction *= -1;
        } else if (middleCard.getType().equals("Skip")) {
            skip = 1;
        } else if (middleCard.getColor().equals("Black")) {

            String[] colors = {"Red", "Yellow", "Green", "Blue"};
            int color = 0;
            if (players.get(playerTurn) instanceof HumanPlayer) {
                System.out.println("Choose a color: 1)Red 2)Yellow 3)Green 4)Blue");
                do {
                    color = scan.nextInt();
                    scan.nextLine();
                    if (color < 1 || color > 4)
                        System.out.println("Invalid input!\nEnter again:");
                } while (color < 1 || color > 4);
                gameColor = colors[color - 1];
            } else {
                color = random.nextInt(4);
                gameColor = colors[color];
            }
            if (middleCard.getType().equals("WildDraw"))
                drawSituation += 4;
        }
        if (!(middleCard.getColor().equals("Black")))
            gameColor = middleCard.getColor();
        changeTurn();
    }

    /*
     * change the turn based on rotation direction
     */
    private void changeTurn() {
        if (direction == -1) {
            playerTurn = (playerTurn + 1) % numOfPlayers;
        } else {
            if (playerTurn == 0)
                playerTurn = numOfPlayers - 1;
            else
                playerTurn--;
        }
    }
}