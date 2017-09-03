/**
 * HitNotifier interface is notify the hit of ball.
 *
 * @author User
 *
 */
public interface HitNotifier {
      /**
       * Add hl as a listener to hit events.
       *
       * @param hl
       *            the block that being hit.
       */
      void addHitListener(HitListener hl);

      /**
       * Remove hl from the list of listeners to hit events.
       *
       * @param hl
       *            the block that being hit.
       */
      void removeHitListener(HitListener hl);
}
