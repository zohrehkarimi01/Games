import java.util.Scanner;

/**
 * HumanPlayer class represents a human player in the game.
 * It Inherits from Player class.
 *
 * @author ZK
 */
public class HumanPlayer extends Player {
    private String name;

    /**
     * create a human player
     *
     * @param numOfPlayer the player's number
     */
    public HumanPlayer(int numOfPlayer) {
        super(numOfPlayer);
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your name:");
        name = scan.nextLine();
    }

    /**
     * get the name of player
     *
     * @return name field
     */
    public String getName() {
        return name;
    }

    /**
     * play in one turn
     *
     * @param middleCard    the middle card
     * @param color         the color of play
     * @param drawSituation if there are draw punishments its value is more than 0
     * @return the card to put in the middle
     */
    @Override
    public Card play(Card middleCard, String color, int drawSituation) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose your card and enter it's number:");
        int choice = -1;
        while (true) {
            choice = scan.nextInt();
            scan.nextLine();
            if (choice > 0 && choice <= playerCards.size()) {
                choice--;
                if (drawSituation > 0) {
                    if (middleCard.getType().equals(playerCards.get(choice).getType()))
                        break;
                } else {
                    if (playerCards.get(choice).getType().equals("WildColor"))
                        break;
                    else if (playerCards.get(choice).getType().equals("WildDraw") && !(possibleActionExceptWild(middleCard, color)))
                        break;
                    else if (playerCards.get(choice) instanceof MoveCard && middleCard.getType().equals(playerCards.get(choice).getType()))
                        break;
                    if (color.equals(playerCards.get(choice).getColor()))
                        break;
                    if (middleCard instanceof NumericCard) {
                        if (middleCard.getScore() == playerCards.get(choice).getScore())
                            break;
                    }
                }
                System.out.println("Invalid input! Enter again:");
            } else
                System.out.println("Wrong input! Enter again:");
        }
        Card selected = playerCards.get(choice);
        playerCards.remove(choice);
        return selected;
    }

    /**
     * print all of player's cards
     */
    public void printAllCards() {
        String ANSI_RESET = "\u001B[0m";
        String currentColor;
        for (int i = 1; i <= 5; i++) {
            for (Card card : playerCards) {
                currentColor = card.getColorToPrint();
                if (i == 3)
                    System.out.print("   " + currentColor + card.getNameToPrint() + ANSI_RESET);
                else
                    System.out.print("   " + currentColor + "                     " + ANSI_RESET);
            }
            System.out.println();
        }
        for (int i = 1; i <= getNumOfCards(); i++)
            System.out.print("             " + i + "          ");
        System.out.println();
    }
}