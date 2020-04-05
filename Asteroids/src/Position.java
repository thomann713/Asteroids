/**
 * Class for the position of an object
 * OR a 2D coordinate system
 */
public class Position {

    private int x;
    private int y;

    /**
     * Constructor to set the position with a 2D coordinate system
     * @param x the x coordinate
     * @param y the y coordinate
     */
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // GETTERS AND SETTERS //

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
