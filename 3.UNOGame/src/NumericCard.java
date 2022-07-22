/**
 * NumericCard class represents the numeric type of cards.
 * It inherits from Card class.
 *
 * @author ZK
 */
public class NumericCard extends Card {

    /**
     * create new numeric card with given number and color
     *
     * @param color  the color of card
     * @param number the number of card
     */
    public NumericCard(String color, int number) {
        super("Numeric", color);
        score = number;
        nameToPrint = colorToPrint + "         " + "\u001B[0m" + " " + number + " " + colorToPrint + "         " + "\u001B[0m";
    }

}
