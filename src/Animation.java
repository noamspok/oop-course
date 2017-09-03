import biuoop.DrawSurface;

/**
*this interface provides basic methods for collidable objects.
*
* @author User
*
*/

public interface Animation {
    /**
     * @param d
     *            - the draw surface object do one frame to game
     * @param dt
     * time;
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * check if the game should stop.
     *
     * @return a boolian value(if the game should stop).
     */
    boolean shouldStop();
}