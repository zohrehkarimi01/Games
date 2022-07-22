/**
 * SkipCard class represents skip type of cards.
 * It inherits from MoveCard class.
 * @author ZK
 */
public class SkipCard extends MoveCard{

    /**
     * create a new skip card
     * @param color the color of card
     */
    public SkipCard(String color){
        super("Skip", color);
        nameToPrint = colorToPrint + "        " + "\u001B[0m" + " Skip " + colorToPrint + "       " + "\u001B[0m";
    }
}