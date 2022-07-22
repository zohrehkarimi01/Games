import java.util.ArrayList;

/**
 * Player class represents a player in the game (human or computer).
 * It holds the player cards.
 *
 * @author ZK
 */
public class Player {

    protected ArrayList<Card> playerCards;
    protected int numberOfPlayer;

    /**
     * create a new player
     *
     * @param numberOfPlayer the number of player in the game
     */
    public Player(int numberOfPlayer) {
        playerCards = new ArrayList<Card>();
        this.numberOfPlayer = numberOfPlayer;
    }

    /**
     * get the player cards
     *
     * @return playerCards field
     */
    public ArrayList<Card> getPlayerCards() {
        return playerCards;
    }

    /**
     * get the number of player
     *
     * @return numberOfPlayer field
     */
    public int getNumberOfPlayer() {
        return numberOfPlayer;
    }

    /**
     * add a card to player cards.
     *
     * @param cardToAdd the card which is going to be added
     */
    public void AddCard(Card cardToAdd) {
        playerCards.add(cardToAdd);
    }

    /**
     * get the number of player cards.
     *
     * @return number of cards that player has
     */
    public int getNumOfCards() {
        return playerCards.size();
    }

    /**
     * Check if there is a Draw type in the player cards.
     *
     * @param type type of card (Draw or WildDraw)
     * @return true if there is such a card, otherwise false.
     */
    public boolean containsDraw(String type) {
        for (Card card : playerCards) {
            if (type.equals(card.getType()))
                return true;
        }
        return false;
    }

    /**
     * play method has been implemented in subclasses.
     * @param middleCard    the middle card
     * @param color         the color of play
     * @param drawSituation if there are draw punishments its value is more than 0
     * @return middle card
     */
    public Card play(Card middleCard, String color, int drawSituation) {
        return middleCard;
    }

    /**
     * Get the total score of player
     *
     * @return score of player
     */
    public int getScore() {
        int score = 0;
        for (Card card : playerCards) {
            score += card.getScore();
        }
        return score;
    }

    /**
     * check the possible actions of player
     *
     * @param middleCard the middle card
     * @param color      the color of play
     * @return true if there is any possible action, otherwise false
     */
    public boolean possibleAction(Card middleCard, String color) {

        for (Card card : playerCards) {
            if (card.getColor().equals("Black"))
                return true;
            if (color.equals(card.getColor())) {
                return true;
            }
            if (middleCard.getType().equals(card.getType())) {
                if (!(middleCard.getType().equals("Numeric")))
                    return true;
                else if (middleCard.getScore() == card.getScore())
                    return true;
            }
        }
        return false;
    }

    /**
     * check the possible actions of player Except WildDraw cards
     *
     * @param middleCard the middle card
     * @param color      the color of playing
     * @return true if there is any possible action(except WildDraw), otherwise false.
     */
    public boolean possibleActionExceptWild(Card middleCard, String color) {

        for (Card card : playerCards) {
            if (color.equals(card.getColor())) {
                return true;
            }
            if (middleCard.getType().equals(card.getType())) {
                if (middleCard instanceof MoveCard)
                    return true;
                if (middleCard.getType().equals("Numeric") && middleCard.getScore() == card.getScore())
                    return true;
                if (middleCard.getType().equals("WildColor"))
                    return true;
            }
        }
        return false;
    }
}
