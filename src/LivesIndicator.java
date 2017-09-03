import biuoop.DrawSurface;
import java.awt.Color;

/**
 * LivesIndicator is used in order to determine how. many lives the user has.
 *
 * @author user
 *
 */
public class LivesIndicator implements Sprite {
      private Counter livesInd;
/**
 * constructor.
 * @param lives
 * the amount of lives left.
 */
      public LivesIndicator(Counter lives) {
            this.livesInd = lives;
      }

      /**
       * draw the sprite to the screen.
       *
       * @param surface
       *            the surface to draw on.
       */
      public void drawOn(DrawSurface surface) {
            DrawSurface d = surface;
            String text = ("Lives: " + this.livesInd.getValue());
            d.setColor(Color.black);
            d.drawText(20, 20, text, 20);

      }

      /**
       * notify the sprite that time has passed. not needed
       * @param dt
       * time.
       */
      public void timePassed(double dt) {

      }

}
