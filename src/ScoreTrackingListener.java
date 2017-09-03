/**
 *
 * @author user.
 *
 */
public class ScoreTrackingListener implements HitListener {
      private Counter currentScore;

      /**
       * constructor.
       * @param scoreCounter
       * score counter.
       */
      public ScoreTrackingListener(Counter scoreCounter) {
            this.currentScore = scoreCounter;
      }

      /**
       *  @param beingHit
       *  being hit.
       *   @param hitter
       *   hitter.
       */
      public void hitEvent(Block beingHit, Ball hitter) {
            if (beingHit instanceof AlienBlock) {
				this.currentScore.increase(100);
           
      }
      }
}
