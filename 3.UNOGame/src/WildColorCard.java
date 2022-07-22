/**
 * WildColorCard class represents wildColor type of cards.
 * It inherits from WildCard class.
 *
 * @author ZK
 */
public class WildColorCard extends WildCard {
    /**
     * create a new wild color card
     */
    public WildColorCard() {
        super("WildColor");
        nameToPrint = colorToPrint + "       " + "\u001B[0m" + " Wild " + colorToPrint + "        " + "\u001B[0m";
    }
}
