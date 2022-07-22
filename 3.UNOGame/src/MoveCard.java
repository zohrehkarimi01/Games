/**
 * MoveCard class represents move type of cards.
 * It inherits from Card class.
 *
 * @author ZK
 */
public class MoveCard extends Card {

    /**
     * create a new move card with given type and color
     *
     * @param type  type of move card (Skip, Reverse or Draw)
     * @param color the color of card
     */
    public MoveCard(String type, String color) {
        super(type, color);
        score = 20;
    }
}
