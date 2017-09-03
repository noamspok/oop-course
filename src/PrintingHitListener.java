/**
 * prints the hits.
 * @author user.
 *
 */
public class PrintingHitListener implements HitListener {
      /**
       * @param beingHit
       * being hit.
       * @param hitter
       * hits.
       */
   public void hitEvent(Block beingHit, Ball hitter) {
      System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
   }
}