/**
 * DrawCard class represents draw type of cards.
 * It inherits from MoveCard class.
 *
 * @author ZK
 */
public class DrawCard extends MoveCard {

    /**
     * create a new draw card with given color
     *
     * @param color the color of card
     */
    public DrawCard(String color) {
        super("Draw", color);
        nameToPrint = colorToPrint + "        " + "\u001B[0m" + " +2 " + colorToPrint + "         " + "\u001B[0m";
    }
}
