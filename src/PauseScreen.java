import biuoop.DrawSurface;


/**
 * PauseScreen is used in order to handle the pause of the game screen.
 *
 * @author user
 */
public class PauseScreen implements Animation {

      /**
       * stop the game response and print the user a message .
       *
       * @param d
       *            the drawsurface.
       * @param dt
       *            number of frames per second.
       */
      public void doOneFrame(DrawSurface d, double dt) {
            d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
      }

      /**
       * stop the game response and print the user a message .
       *
       * @return boolean value if the game should stop.
       */
      public boolean shouldStop() {
            return false;
      }
}
