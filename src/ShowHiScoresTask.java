/**
 * ScoreTrackingListener is used in order to determine the score of the user.
 *
 * @author user
 *
 */
public class ShowHiScoresTask implements Task<Void> {
      private AnimationRunner runner;
      private Animation highScoresAnimation;

      /**
       * draw the sprite to the screen.
       *
       * @param run
       *            scoreCounter.
       * @param highScoresAnim
       *            highScoresAnim.
       */
      public ShowHiScoresTask(AnimationRunner run, Animation highScoresAnim) {
            this.runner = run;
            this.highScoresAnimation = highScoresAnim;
      }

      /**
       * run highScoresAnim.
       *
       * @return Void
       */
      public Void run() {
            this.runner.run(this.highScoresAnimation);
            return null;
      }
}