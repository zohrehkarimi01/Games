/**
 * Card class represents all cards in the UNO game.
 *
 * @author ZK
 */
public class Card {
    protected String type;
    protected int score;
    protected String color;
    protected String colorToPrint;
    protected String nameToPrint;

    /**
     * create a new card with given type and number
     *
     * @param type  The type of card which can be Numeric, Skip, Reverse, Draw, WildColor or WildDraw.
     * @param color The color of card which can be Red, Green, Blue, Yellow or Black
     */
    public Card(String type, String color) {
        this.type = type;
        this.color = color;
        switch (color) {
            case "Blue":
                colorToPrint = "\u001B[44m";
                break;
            case "Red":
                colorToPrint = "\u001B[41m";
                break;
            case "Green":
                colorToPrint = "\u001B[102m";
                break;
            case "Yellow":
                colorToPrint = "\u001B[103m";
                break;
            default:
                colorToPrint = "\u001B[100m";
                break;
        }
    }

    /**
     * get the score of card
     *
     * @return score field
     */
    public int getScore() {
        return score;
    }

    /**
     * get the type of card
     *
     * @return type field
     */
    public String getType() {
        return type;
    }

    /**
     * get color of card
     *
     * @return color field
     */
    public String getColor() {
        return color;
    }

    /**
     * get the name to print card
     *
     * @return nameToPrint field
     */
    public String getNameToPrint() {
        return nameToPrint;
    }

    /**
     * get the color for printing the card
     *
     * @return colorToPrint field
     */
    public String getColorToPrint() {
        return colorToPrint;
    }
}
