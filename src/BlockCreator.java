/**
 * BlockCreator interface is manage the creating of blocks.
 *
 * @author User
 *
 */
public interface BlockCreator {
      /**
       * add the block to the database.
       *
       * @param xpos
       *            x value of block.
       * @param ypos
       *            y value of block.
       * @return a block
       */
      Block create(int xpos, int ypos);
}
