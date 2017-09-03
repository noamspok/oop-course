import java.util.List;
/**
 * LevelInformation interface is manage the info  of the levels.
 *
 * @author User
 *
 */
public interface LevelInformation {
      /**
       *
       * @return number of balls.
       */
   int numberOfBalls();
   /**
    *  The initial velocity of each ball.
    * @return ball velocities.
    */

   List<Velocity> initialBallVelocities();
   /**
    *
    * @return the paddle speed.
    */
   int paddleSpeed();
   /**
    *
    * @return paddle width
    */
   int paddleWidth();
   /**
    *  the level name will be displayed at the top of the screen.
    * @return the level name
    */
   String levelName();
   /**
    *  Returns a sprite with the background of the level.
    * @return a sprite with the background.
    */
   Sprite getBackground();
   /**
    *  The Blocks that make up this level, each block contains
    *  its size, color and location.
    * @return list of blocks.
    */

   List<Block> blocks();
   /**
    *  Number of levels that should be removed before the level is considered to be "cleared".
    *
    * @return Number of levels that should be removed.
    */


   int numberOfBlocksToRemove();
}
