/**
 * this interface is listen for hit of the ball.
 * @author User
 *
 */
public interface HitListener {
    /**
     *  This method is called whenever the beingHit object is hit.
     *  The hitter parameter is the Ball that's doing the hitting
     * @param beingHit
     * the block that being hit.
     * @param hitter
     * the hit ball.
     */
   void hitEvent(Block beingHit, Ball hitter);
}