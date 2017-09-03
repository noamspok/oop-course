
/**
 * this class makes rectangles.
 *
 * @author user
 *
 */
public class Rectangle {
      private Point upperLeft;
      private double width, height;

      /**
       * Create a new rectangle with location and width/height.
       *
       * @param upperLeft
       *            starting point of rectangle
       * @param width
       *            the width of rectangle
       * @param height
       *            the height of rectangle
       */
      public Rectangle(Point upperLeft, double width, double height) {
            this.upperLeft = upperLeft;
            this.height = height;
            this.width = width;
      }

      /**
       * @param line
       * the line intersected.
       * @return a (possibly empty) List of intersection points with the specified
       *         line.
       */
      public java.util.ArrayList<Point> intersectionPoints(Line line) {
            java.util.ArrayList<Point> list = new java.util.ArrayList<Point>();
            Line[] reclines = makeRectanglsLines();
            for (int i = 0; i < 4; i++) {
                  if (reclines[i].isIntersecting(line)) {
                        list.add(reclines[i].intersectionWith(line));
                  }
            }
            return list;
      }

      /**
       * @return the width of the rectangle
       */
      public double getWidth() {
            return this.width;
      }

      /**
       * @return the height of the rectangle
       */
      public double getHeight() {
            return this.height;
      }

      /**
       * @return the upper-left point of the rectangle.
       */
      public Point getUpperLeft() {
            return this.upperLeft;
      }

      /**
       * @return the lines of the rectangle
       */
      public Line[] makeRectanglsLines() {
            Line[] lines = new Line[4];
            Point bottomLeft, bottomRight, upperRight;
            bottomLeft = new Point(this.upperLeft.getX(), upperLeft.getY() + this.height);
            bottomRight = new Point(this.upperLeft.getX() + this.width, upperLeft.getY() + this.height);
            upperRight = new Point(this.upperLeft.getX() + this.width, upperLeft.getY());
            lines[0] = new Line(this.upperLeft, upperRight);
            lines[1] = new Line(bottomLeft, bottomRight);
            lines[2] = new Line(upperRight, bottomRight);
            lines[3] = new Line(this.upperLeft, bottomLeft);
            return lines;
      }
/**
 * searches for the part of rectangle that collides with the collision point.
 * @param collide
 * collision point.
 * @return string with name of collided part.
 */
      public String collidedLine(Point collide) {
            Line[] lines = this.makeRectanglsLines();
            String[] direction = new String[4];
            direction[0] = "up";
            direction[1] = "bottom";
            direction[2] = "right";
            direction[3] = "left";
            if (lines[0].start().equals(collide) || lines[0].end().equals(collide)
                                      || lines[1].start().equals(collide)
                        || lines[1].end().equals(collide)) {
                  return "corner";
            }
            for (int i = 0; i < lines.length; i++) {
                  if (lines[i].isPointInRec(lines[i], collide)) {
                        return direction[i];
                  }
            }
            return null;

      }
      public void SetUpper(Point p){
    	  this.upperLeft=p;
      }
}
