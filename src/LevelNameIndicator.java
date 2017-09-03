import biuoop.DrawSurface;
import java.awt.Color;

/**
 * LevelNameIndicator is used in order to draw the sprite to the screen.
 *
 * @author user
 *
 */
public class LevelNameIndicator implements Sprite {
      private String levelname;
      private int levelnumber;

      /**
       * constructor.
       *
       * @param levname
       *            the level's name.
       */
      public LevelNameIndicator(String levname,int game) {
            this.levelname = levname;
            this.levelnumber=game;
      }

      /**
       * draw the sprite to the screen.
       *
       * @param surface
       *            the surface to draw on.
       */
      public void drawOn(DrawSurface surface) {
            DrawSurface d = surface;
            String text = ("Level Name: " + levelname+(levelnumber+1));
            d.setColor(Color.black);
            d.drawText(d.getWidth() - 300, 20, text, 20);

      }

      /**
       * notify the sprite that time has passed. not needed
       * @param dt
       * time.
       */
      public void timePassed(double dt) {

      }

}
