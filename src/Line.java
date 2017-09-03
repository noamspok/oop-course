
/**
 *
 * @author user this class draws lines and checks middle, meeting point etc..
 */
public class Line {
    // members
    private Point start;
    private Point end;

    /**
     * constructors.
     *
     * @param start
     *            starting point of line
     * @param end
     *            ending point of line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * @param x1
     *            x value of starting point
     * @param y1
     *            y value of starting point
     * @param x2
     *            x value of ending point
     * @param y2
     *            y value of ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * @return the middle point of the line
     */
    public Point middle() {
        double cx = (this.start.getX() + this.end.getX()) / 2;
        double cy = (this.start.getY() + this.end.getY()) / 2;
        return new Point(cx, cy);
    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * @param other
     *            the line to check if intersects with this line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (intersectionWith(other) != null) {
            return true;
        }
        return false;
    }

    /**
     * @param other
     *            the line to check if intersects with this line
     * @return if equal true else false
     */
    public boolean isSlipeEqual(Line other) {
        float a = (float) ((this.start.getX() - this.end.getX()) * (other.start.getY() - other.end.getY()));
        float b = (float) ((this.start.getY() - this.end.getY()) * (other.start.getX() - other.end.getX()));
        float delta = a - b;
        if (delta == 0) {
            return true;
        }
        return false;
    }

    /**
     * @param other
     *            the line to check if intersects with this line
     * @return the intersection point if the lines intersect and null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (isSlipeEqual(other)) {
            return null;
        }
        float deno = (float) ((this.start.getX() - this.end.getX()) * (other.start.getY() - other.end.getY())
                - (this.start.getY() - this.end.getY()) * (other.start.getX() - other.end.getX()));

        float px = (float) (((this.start.getX() * this.end.getY() - this.start.getY() * this.end.getX())
                * (other.start.getX() - other.end.getX())
                - (this.start.getX() - this.end.getX())
                        * (other.start.getX() * other.end.getY()
                              - other.start.getY() * other.end.getX()))
                / deno);

        float py = (float) (((this.start.getX() * this.end.getY() - this.start.getY() * this.end.getX())
                * (other.start.getY() - other.end.getY())
                - (this.start.getY() - this.end.getY())
                        * (other.start.getX() * other.end.getY()
                              - other.start.getY() * other.end.getX()))
                / deno);

        Point intersectionPoint = new Point(px, py);
        boolean isInRec1 = isPointInRec(this, intersectionPoint);
        boolean isInRec2 = isPointInRec(other, intersectionPoint);
        if (isInRec1 && isInRec2) {
            return intersectionPoint;
        }
        return null;
    }

    /**
     * @param l
     *            line to check
     * @param intesctionPoint
     *            the intesctionPoint to check
     * @return true if the point is in rec else false
     */
    public boolean isPointInRec(Line l, Point intesctionPoint) {
        float yMin = (float) Math.min(l.start.getY(), l.end.getY());
        float xMin = (float) Math.min(l.start.getX(), l.end.getX());
        float yMax = (float) Math.max(l.start.getY(), l.end.getY());
        float xMax = (float) Math.max(l.start.getX(), l.end.getX());
        if (intesctionPoint.getY() <= yMax && intesctionPoint.getY() >= yMin && intesctionPoint.getX() <= xMax
                && intesctionPoint.getX() >= xMin) {
            return true;
        }
        return false;
    }

    /**
     * @param other
     *            the other line
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        boolean isSlipeEqual = isSlipeEqual(other);
        float m = (float) ((other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX()));
        float thism = (float) ((this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX()));
        float n = (float) ((-m) * other.start.getX() + other.start.getY());
        float thisn = (float) ((-thism) * this.start.getX() + this.start.getY());
        return isSlipeEqual && n == thisn;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the
     * line.
     *
     * @param rect
     *            the rectangle to check intersection with.
     * @return null or the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point minPoint = null;
        double min = 0;
        for (int i = 0; i < rect.intersectionPoints(this).size(); i++) {
            if (this.start.distance(rect.intersectionPoints(this).get(i)) < min || min == 0) {
                min = this.start.distance(rect.intersectionPoints(this).get(i));
                minPoint = rect.intersectionPoints(this).get(i);

            }
        }

        return minPoint;

    }
}