
/**
 *
 * @author Noam
 * this class makes a point with x,y values and can check the distance between 2 lines
 */

public class Point {
    // members
    private  double x;
    private double y;

    /**
     *  constructor.
     * @param x
     * x value of point
     * @param y
     * y value of point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other
     * the other point
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * @param other
     * the other point
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        if (dx == 0 && dy == 0) {
            return true;
        }
        return false;
    }

    /**
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }
/**
 * @return the y value of this point
 */
    public double getY() {
        return this.y;
    }

}