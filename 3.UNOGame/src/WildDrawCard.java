/**
 * WildColorCard class represents wild draw type of cards.
 * It inherits from WildCard class.
 *
 * @author ZK
 */
public class WildDrawCard extends WildCard {
    /**
     * create a new wild draw card
     */
    public WildDrawCard() {
        super("WildDraw");
        nameToPrint = colorToPrint + "        " + "\u001B[0m" + " +4 " + colorToPrint + "         " + "\u001B[0m";
    }
}
