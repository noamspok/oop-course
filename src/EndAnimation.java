import biuoop.DrawSurface;

/**
 * @author user
 *
 *         class handles the end of the game
 */
public class EndAnimation implements Animation {
      private Counter endScore;
      private boolean win;

      /**
       * constructor.
       *
       * @param score
       *            - the animation of the game.
       * @param gWin
       *            -a boolean value. the use win or not.
       */
      public EndAnimation(Counter score, boolean gWin) {
            this.endScore = score;
            this.win = gWin;
      }

      /**
       * do the step at the end of the game.
       *
       * @param d
       *            - the DrawSurface of the game
       * @param dt
       *  the time def.
       */
      public void doOneFrame(DrawSurface d, double dt) {
            if (win) {
                  d.drawText(10, d.getHeight() / 2, "You Win! Your score is: " + endScore, 32);
            } else {
                  d.drawText(10, d.getHeight() / 2, "Game Over. Your score is: " + endScore.getValue(), 32);
            }
      }

      /**
       * do the step at the end of the game. return a flag for ending the game.
       *
       * @return when to stop.
       */
      public boolean shouldStop() {
            return false;
      }

}
