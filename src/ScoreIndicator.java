import biuoop.DrawSurface;
import java.awt.Color;

/**
 * ScoreIndicator is used in order to determine the score of the user.
 *
 * @author user
 *
 */
public class ScoreIndicator implements Sprite {
      private Counter scoreInd;
/**
 * constructor.
 * @param score
 * the score.
 */
      public ScoreIndicator(Counter score) {
            this.scoreInd = score;
      }

      /**
       * draw the sprite to the screen.
       *
       * @param surface
       *            the surface to draw on.
       */
      public void drawOn(DrawSurface surface) {
            DrawSurface d = surface;
            d.setColor(Color.gray.brighter());
            d.drawRectangle(0, 0, d.getWidth(), 20);
            d.setColor(Color.gray.brighter());
            d.fillRectangle(0, 0, d.getWidth(), 20);
            String text = ("Score: " + this.scoreInd.getValue());
            d.setColor(java.awt.Color.black);
            d.drawText(d.getWidth() / 2 - 30, 20, text, 20);

      }

      /**
       * notify the sprite that time has passed. not needed
       * @param dt
       * time.
       */
      public void timePassed(double dt) {

      }

}
