
/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 *
 * @author user
 */
public class Velocity {
      /**
       * Decelerations.
       */
      private double dx, dy;

      /**
       * constructor.
       *
       * @param dx
       *            Initializes the horizontal's velocity.
       * @param dy
       *            initializes the vertical's velocity.
       */
      public Velocity(double dx, double dy) {
            this.dx = dx;
            this.dy = dy;
      }

      /**
       * Take a point with position (x,y) and return a new point with position.
       * (x+dx, y+dy)
       *
       * @param p
       *            the point where the ball is now
       * @return new point for the ball to go to
       */

      public Point applyToPoint(Point p) {
            Point newPosition;
            newPosition = new Point(p.getX() + this.dx, p.getY() + this.dy);
            return newPosition;
      }

      /**
       * this method gets an angle and speed and conversions it to speed in the
       * vertical and horizontal directions.
       *
       * @param angle
       *            the angle the ball moves at
       * @param speed
       *            the speed the ball moves in that angle
       * @return the vertical and horizontal directions and speed
       */
      public Velocity fromAngleAndSpeed(double angle, double speed) {
            angle = Math.toRadians(angle);
            double dx1 = Math.sin(angle) * speed;
            double dy1 = Math.cos(angle) * speed;
            return new Velocity(dx1, dy1);
      }

      /**
       * this method gets the velocity in the horizontal direction.
       *
       * @return Dx
       */
      public int getDx() {
            return (int) this.dx;
      }

      /**
       * this method gets the velocity in the vertical direction.
       *
       * @return Dy
       */
      public int getDy() {
            return (int) this.dy;
      }
}