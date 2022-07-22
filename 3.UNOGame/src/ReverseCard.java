/**
 * ReverseCard class represents reverse type of cards.
 * It inherits from MoveCard class.
 *
 * @author ZK
 */
public class ReverseCard extends MoveCard {

    /**
     * create a new reverse card
     *
     * @param color the color of card
     */
    public ReverseCard(String color) {
        super("Reverse", color);
        nameToPrint = colorToPrint + "      " + "\u001B[0m" + " Reverse " + colorToPrint + "      " + "\u001B[0m";
    }
}
