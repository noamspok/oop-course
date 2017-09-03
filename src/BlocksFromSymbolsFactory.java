
import java.util.Map;
import java.util.TreeMap;

/**
 * a BlocksFromSymbolsFactory a mechanism (object) with a method that will get a
 * symbol and create the desired block.
 *
 * @author user
 *
 */
public class BlocksFromSymbolsFactory {

      private Map<String, Integer> spacerWidths = new TreeMap<String, Integer>();
      private Map<String, BlockCreator> blockCreators = new TreeMap<String, BlockCreator>();

      /**
       * get the remaining blocks.
       *
       * @param s
       *            the input string
       * @return true if 's' is a valid space symbol
       */
      public boolean isSpaceSymbol(String s) {

            if (spacerWidths.containsKey(s)) {
                  return true;

            }
            return false;
      }

      /**
       * get the remaining blocks.
       *
       * @param s
       *            the input string
       * @return true if 's' a valid block symbol.
       */
      public boolean isBlockSymbol(String s) {
            if (blockCreators.containsKey(s)) {
                  return true;
            }
            return false;
      }

      /**
       * Return a block according to the definitions associated. with symbol s.
       * The block will be located at position (xpos, ypos).
       *
       * @param s
       *            the input string
       * @param x
       *            x val
       * @param y
       *            y val
       * @return block.
       */
      public Block getBlock(String s, int x, int y) {
            return this.blockCreators.get(s).create(x, y);
      }

      /**
       * Returns the width in pixels associated with the given spacer-symbol. with
       * symbol s. The block will be located at position (xpos, ypos).
       *
       * @param s
       *            the input string
       * @return the width in pixels associated
       */
      public int getSpaceWidth(String s) {
            return this.spacerWidths.get(s);
      }

      /**
       * @param s
       *            the input string
       * @param b
       *            the input string
       */
      public void addBlocks(String s, BlockCreator b) {
            blockCreators.put(s, b);
      }

      /**
       * @param s
       *            the input string
       * @param size
       *            the size
       */
      public void addSpacess(String s, Integer size) {
            spacerWidths.put(s, size);
      }
}