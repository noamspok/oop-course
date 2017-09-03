
/**
 * a BlockRemover is in charge of removing blocks from the gameLevel, as well as
 * keeping count of the number of blocks that remain.
 *
 * @author user
 *
 */

public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor.
     *
     * @param gameLevel
     *            the frequency per second.
     * @param removedBlocks
     *            the frequency per second. .
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * handle the event of hit.
     *
     * @param beingHit
     *            - the block which the ball collide him.
     * @param hitter
     *            - the ball which collide the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
    	
        if (beingHit.getHitPoints() == 1) {
            gameLevel.removeCollidable(beingHit);
            gameLevel.removeSprite(beingHit);
            hitter.removeHitListener(this);
            remainingBlocks.decrease(1);
            beingHit.removeHitListener(this);
        }
    }
/**
 *
 * @return the remaining blocks.
 */

    public int getRemainingBlocks() {
        return this.remainingBlocks.getValue();
    }
}
