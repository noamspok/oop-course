/**
 * this class keeps the type of object, and point of collision.
 *
 * @author User
 *
 */

public class CollisionInfo {
    private Point collisionP;
    private Collidable collObject;

    /**
     * constructor.
     *
     * @param collision
     *            point of collision
     * @param object
     *            object that collides
     */
    public CollisionInfo(Point collision, Collidable object) {
        this.collisionP = collision;
        this.collObject = object;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionP;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collObject;
    }
}
