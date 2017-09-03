
/**
 * this interface provides basic methods for collidable objects.
 *
 * @author User
 *
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given
     * velocity.
     * @param hitter
     * the hitter.
     * @param collisionPoint
     *            the collision point
     * @param currentVelocity
     *            the current velocity of the object
     * @return the new velocity expected after the hit (based on the force the
     *         object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
