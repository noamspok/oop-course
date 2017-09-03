
/**
 * a BallRemover is in charge of removing balls from the gameLevel, as well as
 * keeping count of the number of balls that remain.
 *
 * @author user
 *
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls = new Counter();

    /**
     * constructors.
     *
     * @param gameLevel
     *            the frequency per second.
     * @param removedBalls
     *            the frequency per second. .
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    /**
     * handle the event of hit.
     *
     * @param beingHit
     *            - the block which the ball collide him.
     * @param hitter
     *            - the ball which hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
            hitter.removeFromGame(this.gameLevel);
            remainingBalls.decrease(1);
            gameLevel.getBallList().remove(hitter);

    }

}
