/**
 * WildCard class represents wild type of cards.
 * It inherits from Card class.
 *
 * @author ZK
 */
public class WildCard extends Card {

    /**
     * create a new wild card
     *
     * @param type type of wild card (WildColor or WildDraw)
     */
    public WildCard(String type) {
        super(type, "Black");
        score = 50;
    }
}
