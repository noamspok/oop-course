
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * @author user
 *
 *         class makes a ball with possibilities to move and draw itself
 */
public class Ball implements Sprite, HitNotifier {
      /**
       * Decelerations.
       */
      private List<HitListener> hitListeners = new ArrayList<>();
      private GameEnvironment game;
      private Point center;
      private int r;
      private java.awt.Color color;
      private Velocity v;

      /**
       * constructors.
       *
       * @param x
       *            center point
       * @param y
       *            center point
       * @param r
       *            the size of radius
       * @param color
       *            the color of the circle
       * @param game
       *            the game environment
       */
      public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment game) {
            this.center = new Point((double) x, (double) y);
            this.r = r;
            this.color = color;
            this.game = game;
      }

      /**
       * constructor without game environment.
       *
       * @param x
       *            center point
       * @param y
       *            center point
       * @param r
       *            the size of radius
       * @param color
       *            the color of the circle
       */
      public Ball(int x, int y, int r, java.awt.Color color) {
            this.center = new Point((double) x, (double) y);
            this.r = r;
            this.color = color;
      }

      /**
       * @param center
       *            x,y for the center of circle
       * @param r
       *            size of radius
       * @param color
       *            color of circle
       */
      public Ball(Point center, int r, java.awt.Color color) {
            this.r = r;
            this.color = color;
      }

      /**
       * accessories.
       *
       * @return the x value of the center of the ball
       */
      public int getX() {
            return (int) this.center.getX();
      }

      /**
       * @return the y value of the center of the ball
       */
      public int getY() {
            return (int) this.center.getY();
      }

      /**
       * @return the radius size of the ball
       */
      public int getSize() {
            return (int) this.r;
      }

      /**
       * @return the color of the ball
       */
      public java.awt.Color getColor() {
            return this.color;
      }

      /**
       * @param surface
       *            get size of surface
       */

      public void drawOn(DrawSurface surface) {
            DrawSurface d = surface;
            d.setColor(Color.black);
            d.drawCircle(this.getX(), this.getY(), this.getSize());
            d.setColor(this.getColor());
            d.fillCircle(this.getX(), this.getY(), this.getSize());
      }

      /**
       * @param vel
       *            gets the velocity for this ball
       */
      public void setVelocity(Velocity vel) {
            this.v = vel;
      }

      /**
       * @param dx
       *            sets the velocity of the ball for horizontal direction
       * @param dy
       *            sets the velocity of the ball for vertical direction
       */
      public void setVelocity(double dx, double dy) {
            this.v = new Velocity(dx, dy);
      }

      /**
       * @return velocity of the ball
       */
      public Velocity getVelocity() {
            return this.v;
      }

      /**
       * moves the ball to its new place on the screen.
       */
      public void moveOneStep() {
            Line trajectory = new Line(this.center.getX(), this.center.getY(),
                        this.center.getX() + this.getVelocity().getDx(), this.center.getY()
                        + this.getVelocity().getDy());
            if (this.game.getClosestCollision(trajectory) != null) {
                  if (this.game.getClosestCollision(trajectory).collisionPoint().distance(this.center) < 1.5
                              * this.getSize()) {
                        this.setNewVelocity(trajectory);
                        Line trajectory2 = new Line(this.center.getX(), this.center.getY(),
                                    this.center.getX() + this.getVelocity().getDx(),
                                    this.center.getY() + this.getVelocity().getDy());
                        if (this.game.getClosestCollision(trajectory2) != null) {
                              if (this.game.getClosestCollision(trajectory2).collisionPoint().
                                          distance(this.center) < 1.5
                                          * this.getSize()) {
                                    this.center = this.getVelocity().applyToPoint(this.center);
                              } else {
                                    this.center = this.getNewcCenter(trajectory);
                              }
                        } else {
                              this.center = this.getVelocity().applyToPoint(this.center);
                        }
                  } else {
                        this.center = this.getNewcCenter(trajectory);
                  }

            } else {
                  this.center = this.getVelocity().applyToPoint(this.center);
            }

      }

      /**
       * sets the new volocity.
       *
       * @param trajectory
       *            the line to check collision with.
       */

      public void setNewVelocity(Line trajectory) {

           this.setVelocity(this.game.getClosestCollision(trajectory).collisionObject().hit(this,
                        this.game.getClosestCollision(trajectory).collisionPoint(), this.getVelocity()));

      }

      /**
       * sets the new center.
       *
       * @param trajectory
       *            the line to check collision with.
       * @return the new center.
       */

      public Point getNewcCenter(Line trajectory) {

            if (this.game.getClosestCollision(trajectory).collisionObject().getCollisionRectangle()
                        .collidedLine(this.game.getClosestCollision(trajectory).collisionPoint()).
                        equals("up")) {
                  return new Point(this.game.getClosestCollision(trajectory).collisionPoint().getX(),
                              this.game.getClosestCollision(trajectory).collisionPoint().getY() - this.r - 1);
            }
            if (this.game.getClosestCollision(trajectory).collisionObject().getCollisionRectangle()
                        .collidedLine(this.game.getClosestCollision(trajectory).collisionPoint()).
                        equals("bottom")) {
                  return new Point(this.game.getClosestCollision(trajectory).collisionPoint().getX(),
                              this.game.getClosestCollision(trajectory).collisionPoint().getY() + this.r + 1);
            }
            if (this.game.getClosestCollision(trajectory).collisionObject().getCollisionRectangle()
                        .collidedLine(this.game.getClosestCollision(trajectory).collisionPoint()).
                        equals("right")) {
                  return new Point(this.game.getClosestCollision(trajectory).collisionPoint().getX() + this.r + 1,
                              this.game.getClosestCollision(trajectory).collisionPoint().getY());
            }
            if (this.game.getClosestCollision(trajectory).collisionObject().getCollisionRectangle()
                        .collidedLine(this.game.getClosestCollision(trajectory).
                                    collisionPoint()).equals("left")) {
                  return new Point(this.game.getClosestCollision(trajectory).collisionPoint().getX() - this.r - 1,
                              this.game.getClosestCollision(trajectory).collisionPoint().getY() - this.r - 1);
            }
            return this.center;
      }

      /**
       * setting the game environment.
       *
       * @param game1
       *            the game in which all the data is stored.
       */
      public void setGameEnvironment(GameEnvironment game1) {
            this.game = game1;
      }

      /**
       * lets the class know that time has come to make another step.
       * @param dt
       * time
       */
      public void timePassed(double dt) {
            this.moveOneStep();
      }

      /**
       * adding the class to the game.
       *
       * @param g
       *            the game in which the game is stored in.
       */
      public void addToGame(GameLevel g) {
            g.addSprite(this);
      }

      /**
       * adding an hit listeners.
       *
       * @param hl
       *            an hit listener.
       */

      public void addHitListener(HitListener hl) {
            this.hitListeners.add(hl);

      }

      /**
       * remove an hit listener.
       *
       * @param hl
       *            an hit listener to be removed.
       */
      public void removeHitListener(HitListener hl) {
            if (hl != null) {
                  this.hitListeners.remove(hl);
            }

      }

      /**
       * remove a sprite from game level.
       *
       * @param g
       *            the game to be removed from.
       */

      public void removeFromGame(GameLevel g) {
            g.removeSprite(this);
      }
}
