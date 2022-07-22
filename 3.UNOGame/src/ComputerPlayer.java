/**
 * ComputerPlayer class represents a bot player in the game.
 * It Inherits from Player class.
 *
 * @author ZK
 */
public class ComputerPlayer extends Player {

    /**
     * create a computer player
     *
     * @param numOfPlayer the player's number
     */
    public ComputerPlayer(int numOfPlayer) {
        super(numOfPlayer);
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
        int i;
        for (i = 0; i < playerCards.size(); i++) {
            if (drawSituation > 0) {
                if (middleCard.getType().equals(playerCards.get(i).getType()))
                    break;
            } else {
                if (color.equals(playerCards.get(i).getColor())) {
                    break;
                }
                if (playerCards.get(i) instanceof MoveCard && middleCard.getType().equals(playerCards.get(i).getType())) {
                    break;
                }
                if (middleCard instanceof NumericCard && middleCard.getScore() == playerCards.get(i).getScore()) {
                    break;
                }
                if (playerCards.get(i).getType().equals("WildColor"))
                    break;
                else if (playerCards.get(i).getType().equals("WildDraw") && !(possibleActionExceptWild(middleCard, color)))
                    break;
            }
        }
        Card selected = playerCards.get(i);
        playerCards.remove(i);
        return selected;
    }
}
