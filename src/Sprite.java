import biuoop.DrawSurface;

/**
 * this interface helps us control the visual part of the game.
 *
 * @author User
 *
 */
public interface Sprite {
      /**
       * draw the sprite to the screen.
       *
       * @param d
       *            the surface to draw on.
       */
      void drawOn(DrawSurface d);

      /**
       * notify the sprite that time has passed.
       * @param dt
       * time.
       */
      void timePassed(double dt);
}